package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.UserAuditFile;

public interface UserAuditFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuditFile record);

    int insertSelective(UserAuditFile record);

    UserAuditFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAuditFile record);

    int updateByPrimaryKey(UserAuditFile record);
}