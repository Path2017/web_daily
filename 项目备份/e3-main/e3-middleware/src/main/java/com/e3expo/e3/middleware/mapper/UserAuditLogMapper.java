package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.UserAuditLog;

public interface UserAuditLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuditLog record);

    int insertSelective(UserAuditLog record);

    UserAuditLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAuditLog record);

    int updateByPrimaryKey(UserAuditLog record);
}