package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.UserAuditRemarkLog;

public interface UserAuditRemarkLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuditRemarkLog record);

    int insertSelective(UserAuditRemarkLog record);

    UserAuditRemarkLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAuditRemarkLog record);

    int updateByPrimaryKey(UserAuditRemarkLog record);
}