package com.e3expo.mms.bean.mapper;

import com.e3expo.mms.bean.model.Application;
import com.e3expo.mms.bean.param.DownloadListParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DownloadMapper {
    /**
     * 分页条件查询所有系统管理员的下载列表
     *
     * @return 管理员下载列表
     */
    List<Application> pageConditionQuerySysAdminDownloadList();

    /**
     * 分页条件查询所有的下载列表
     *
     * @param param 参数
     * @return 分页数据
     */
    List<Application> pageConditionQueryDownloadList(DownloadListParam param);

    /**
     * 分页条件查询所有的下载列表总数
     *
     * @param param 参数
     * @return 总条目数
     */
    int getDownloadListTotalNumber(DownloadListParam param);

    /**
     * 查询指定人，指定设计图的状态
     *
     * @param applicant 用户id
     * @param designId  设计图id
     * @return 状态
     */
    byte getApplicationStatus(@Param("applicant") int applicant,
                              @Param("designId") int designId);
}