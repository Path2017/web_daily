package com.e3expo.mms.bean.dao;


import com.e3expo.mms.Exception.ApprovedApplicationMultipleException;
import com.e3expo.mms.bean.mapper.ApplicationMapper;
import com.e3expo.mms.bean.model.Application;
import com.e3expo.mms.bean.param.ApplicationParam;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.e3expo.mms.bean.enumeration.ApplicationStatusEnum.*;

@Repository
@EnableCaching
public class ApplicationDao {

    @Autowired
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
        this.mapper = this.sqlSession.getMapper(ApplicationMapper.class);
    }

    private ApplicationMapper mapper;
    private SqlSessionTemplate sqlSession;

    /**
     * 返回下指定用户的指定设计图的通过的下载审批，如果没有下载权就返回空。
     *
     * @param userId   用户id
     * @param designId 设计id
     * @return 如果没有下载权就返回空
     */
    public Application getApprovedApplication(int userId, int designId) throws ApprovedApplicationMultipleException {
        List<Application> application = mapper.getApplication(userId, designId, APPROVED.getValue());
        if (application != null) {
            if (application.size() == 1) {
                return application.get(0);
            } else if (application.size() == 0) {
                return null;
            } else {
                throw new ApprovedApplicationMultipleException();
            }
        }
        return null;
    }


    /**
     * 返回下指定用户的指定设计图的通过的下载审批，如果没有下载权就返回空。
     *
     * @param designId  设计图
     * @param applicant 申请人
     * @return 返回申请状态
     */
    public byte getApplicationStatus(int designId, int applicant) {
        Application latestApplication = mapper.getLatestApplication(designId, applicant);
        if (latestApplication == null) {
            return NOT_APPROVED.getValue();
        }
        return latestApplication.getStatus();
    }

    public void createApplication(int applicant, int designId) {
        mapper.singleInsert(applicant, designId, System.currentTimeMillis());
    }

    public int getApplicationTotalNumberByParam(ApplicationParam param) {
        return mapper.getApplicationTotalNumberByParam(param);
    }

    public List<Application> pageConditionQuery(ApplicationParam param) {
        return mapper.pageConditionQuery(param);
    }

    /**
     * 默认插入到申请表中
     *
     * @param userList 用户ID列表
     * @param designID 设计ID
     */
    public void defaultInsert(List<Integer> userList, int designID) {
        mapper.batchApplicantInsert(userList, designID, DEFAULT_APPROVED.getValue(), System.currentTimeMillis());
    }

    /**
     * 更新申请状态
     *
     * @param applicationId 申请id
     * @param status        状态
     */
    public void updateApplicationStatus(int applicationId, byte status) {
        mapper.updateApplicationStatus(applicationId, status, System.currentTimeMillis());
    }

    /**
     * 根据设计Id删除申请表中数据
     *
     * @param designID 设计ID
     */
    public void deleteApplicationByDesignId(int designID) {
        mapper.updateApplicationStatusByDesignId(designID, DELETED.getValue(), System.currentTimeMillis());
    }

    /**
     * 给本地管理员用户批量插入默认许可
     *
     * @param designList 设计图id列表
     * @param userId     用户id
     */
    public void defaultInsertForLocalAdmin(List<Integer> designList, int userId) {
        mapper.batchDesignInsert(designList, userId, DEFAULT_APPROVED.getValue(), System.currentTimeMillis());

    }
}
