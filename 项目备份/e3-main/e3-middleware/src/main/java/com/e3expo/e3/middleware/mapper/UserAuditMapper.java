package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.UserAudit;
import com.e3expo.e3.model.view.UserAuditDetailView;

public interface UserAuditMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAudit record);

    int insertSelective(UserAudit record);

    UserAudit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAudit record);

    int updateByPrimaryKey(UserAudit record);
    
}