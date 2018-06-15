package com.e3expo.mms.bean.service;

import com.e3expo.mms.Exception.DesignIsNotExistException;
import com.e3expo.mms.bean.dao.ApplicationDao;
import com.e3expo.mms.bean.dao.CityDao;
import com.e3expo.mms.bean.dao.DesignDao;
import com.e3expo.mms.bean.model.Application;
import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.param.ApplicationParam;
import com.e3expo.mms.bean.service.oss.OSSManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import static com.e3expo.mms.bean.enumeration.ApplicationStatusEnum.APPLYING;
import static com.e3expo.mms.bean.enumeration.ApplicationStatusEnum.APPROVED;
import static com.e3expo.mms.bean.enumeration.ApplicationStatusEnum.NOT_APPROVED;
import static com.e3expo.mms.config.constant.DesignConstant.OSS_IMG_EXPIRATION_MILLISECONDS;

@Service
public class ApplicationService {


    private final ApplicationDao applicationDao;
    private final DesignDao designDao;
    private final CityDao cityDao;

    @Autowired
    public ApplicationService(ApplicationDao applicationDao, DesignDao designDao, CityDao cityDao) {
        this.applicationDao = applicationDao;
        this.designDao = designDao;
        this.cityDao = cityDao;
    }

    /**
     * 创建申请
     * @param user     申请用户
     * @param designId 设计ID
     */
    public void createApplication(User user, int designId) {
        // 查询设计
        // designDao.getDesignDetailById(designId);
        if (!designDao.checkDesignExist(designId)) {
            throw new DesignIsNotExistException();
        }
        // 查询申请表
        byte applicationStatus = applicationDao.getApplicationStatus(designId, user.getId());
        if (applicationStatus == APPROVED.getValue() || applicationStatus == APPLYING.getValue()) {
            // 如果是申请中或者已获授权，就直接跳过。
            return;
        }
        if (applicationStatus == NOT_APPROVED.getValue()) {
            // 创建申请
            applicationDao.createApplication(user.getId(), designId);
        }
    }

    /**
     * 分页条件查询
     * @param param 参数
     * @param model model
     */
    public void pageConditionQuery(ApplicationParam param, Model model) {
        param.setTotal(applicationDao.getApplicationTotalNumberByParam(param));
        List<Application> applicationList = applicationDao.pageConditionQuery(param);
        // 遍历保存生成cover的href
        // 设置临时访问url
        applicationList
                .forEach(application -> application.setCoverTemporaryUrl(
                        OSSManager.getInstance().getClient().generatePresignedUrl(
                                application.getDesign().getCity().getBucket(),
                                application.getDesign().getCoverOssKey(),
                                new Date(System.currentTimeMillis() + OSS_IMG_EXPIRATION_MILLISECONDS)).toExternalForm()));
        param.setData(applicationList);
        model.addAttribute("page", param);
    }

    /**
     * 向model设置下拉选项
     * @param model MODEL
     */
    public void setCitiesAndApplicationStatuses(Model model) {
        model.addAttribute("cityList", cityDao.getAllCities());
        model.addAttribute("statusList", EnumSet.of(NOT_APPROVED, APPLYING, APPROVED));
    }

    /**
     * 通过下载审批
     * @param applicationId 申请ID
     */
    public void approveApplication(int applicationId) {
        applicationDao.updateApplicationStatus(applicationId, APPROVED.getValue());
    }

    /**
     * 不通下载过审批
     * @param applicationId 申请ID
     */
    public void rejectApplication(int applicationId) {
        applicationDao.updateApplicationStatus(applicationId, NOT_APPROVED.getValue());
    }
}