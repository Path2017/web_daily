package com.e3expo.mms.bean.mapper;

import com.e3expo.mms.bean.model.Application;
import com.e3expo.mms.bean.param.ApplicationParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplicationMapper {

    /**
     * 查询指定的审批
     *
     * @param applicant 申请人id
     * @param designId  设计图id
     * @param status    审批状态
     * @return 查询结果
     */
    List<Application> getApplication(@Param("applicant") int applicant,
                                     @Param("designId") int designId,
                                     @Param("status") byte status);

    /**
     * 查询指定的审批
     *
     * @param applicant 申请人id
     * @param designId  设计图id
     * @return 查询结果
     */
    Application getLatestApplication(@Param("designId") int designId,
                                     @Param("applicant") int applicant);

    /**
     * 单条插入
     *
     * @param applicant  申请人
     * @param designId   设计id
     * @param createTime 创建时间
     */
    void singleInsert(@Param("applicant") int applicant,
                      @Param("designId") int designId,
                      @Param("createTime") long createTime);

    /**
     * 查询每个申请人最新的一条申请记录， 返回其总数
     *
     * @param param 请求参数
     * @return 总条目数
     */
    int getApplicationTotalNumberByParam(ApplicationParam param);

    /**
     * 分页条件查询申请记录
     *
     * @param param 请求参数
     * @return 分页条件查询结果
     */
    List<Application> pageConditionQuery(ApplicationParam param);

    /**
     * 多申请人批量插入
     *
     * @param userList   用户列表
     * @param designId   设计id
     * @param status     状态
     * @param createTime 当前时间戳
     */
    void batchApplicantInsert(@Param("userList") List<Integer> userList,
                              @Param("designId") int designId,
                              @Param("status") byte status,
                              @Param("createTime") long createTime);

    /**
     * 更新申请状态
     *
     * @param applicationId 申请id
     * @param status        状态
     * @param handlingTime  处理时间
     */
    void updateApplicationStatus(@Param("applicationId") int applicationId,
                                 @Param("status") byte status,
                                 @Param("handlingTime") long handlingTime);

    /**
     * 根据设计id更新状态
     *
     * @param designId     设计ID
     * @param status       状态值
     * @param handlingTime 处理时间
     */
    void updateApplicationStatusByDesignId(@Param("designId") int designId,
                                           @Param("status") byte status,
                                           @Param("handlingTime") long handlingTime);

    /**
     * 给本地管理员用户批量插入默认许可
     *
     * @param designList 设计图id列表
     * @param userId     用户id
     * @param status     状态
     * @param createTime 创建时间
     */
    void batchDesignInsert(@Param("designList") List<Integer> designList,
                           @Param("userId") int userId,
                           @Param("status") byte status,
                           @Param("createTime") long createTime);
}