package com.e3expo.mms.bean.service;


import com.e3expo.mms.Exception.PermissionDeniedException;
import com.e3expo.mms.bean.dao.DesignDao;
import com.e3expo.mms.bean.dao.DownloadDao;
import com.e3expo.mms.bean.model.Application;
import com.e3expo.mms.bean.model.Design;
import com.e3expo.mms.bean.model.DesignHistory;
import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.param.DownloadListParam;
import com.e3expo.mms.bean.service.oss.OSSManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.e3expo.mms.bean.enumeration.DesignOperationEnum.DOWNLOAD_SUCCESSFULLY;
import static com.e3expo.mms.bean.enumeration.DesignOrderTypeEnum.AREA_ASC;
import static com.e3expo.mms.bean.enumeration.DesignOrderTypeEnum.AREA_DESC;
import static com.e3expo.mms.bean.enumeration.DesignOrderTypeEnum.DOWNLOADS_DESC;
import static com.e3expo.mms.bean.enumeration.DesignOrderTypeEnum.PRICE_ASC;
import static com.e3expo.mms.bean.enumeration.DesignOrderTypeEnum.PRICE_DESC;
import static com.e3expo.mms.bean.enumeration.DesignOrderTypeEnum.VIEWS_DESC;
import static com.e3expo.mms.bean.enumeration.DesignOrderTypeEnum.getDesignOrderTypeEnumByValue;
import static com.e3expo.mms.config.constant.DesignConstant.OSS_IMG_EXPIRATION_MILLISECONDS;

@Service
public class MyDownloadService {

    private final DesignDao designDao;

    private final DownloadDao downloadDao;

    @Autowired
    public MyDownloadService(DesignDao designDao, DownloadDao downloadDao) {
        this.designDao = designDao;
        this.downloadDao = downloadDao;
    }

    /**
     * 分页条件查询所有下载
     *
     * @param user  登录用户
     * @param param 参数
     * @param model model
     */
    public void pageConditionQuery(User user, DownloadListParam param, Model model) {
        // 因为有分页，所以应该是必须要在同一张表中，必须要有下载表，向下载表中插入记录的有两个地方：1，新建；2：审批通过
        param.setUserId(user.getId());
        param.setTotal(downloadDao.getDownloadListTotalNumber(param));
        List<Application> applicationList = downloadDao.pageConditionQueryDownloadList(param);

        sortListByOrderType(param.getOrderTypeValue(), applicationList);
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
     * 根据排序方式值将Design列表谱排序。
     * @param orderType 排序方式值
     * @param list      design列表
     */
    @SuppressWarnings("Duplicates")
    private void sortListByOrderType(byte orderType, List<Application> list) {
        Comparator comparator = null;
        switch (getDesignOrderTypeEnumByValue(orderType)) {
            case VIEWS_DESC:
                comparator = VIEWS_DESC.getComparator();
                break;
            case DOWNLOADS_DESC:
                comparator = DOWNLOADS_DESC.getComparator();
                break;
            case PRICE_DESC:
                comparator = PRICE_DESC.getComparator();
                break;
            case PRICE_ASC:
                comparator = PRICE_ASC.getComparator();
                break;
            case AREA_DESC:
                comparator = AREA_DESC.getComparator();
                break;
            case AREA_ASC:
                comparator = AREA_ASC.getComparator();
                break;
            default:
                break;
        }
        Collections.sort(list, comparator);
    }

    /**
     * 获取下载设计图相关信息
     *
     * @param user     登录用户
     * @param designId 设计图id
     * @return 要下载的设计信息
     */
    public Design getDownloadDesign(User user, int designId) {
        if (!downloadDao.checkUserHasDownloadRight(user.getId(), designId)) {
            throw new PermissionDeniedException();
        }
        Design design = designDao.getDesignDetailById(designId);
        designDao.increaseDownloads(designId);
        designDao.insertIntoDesignHistory(new DesignHistory(System.currentTimeMillis(), user.getId(), designId, DOWNLOAD_SUCCESSFULLY.getId()));
        return design;
    }
}