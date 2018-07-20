package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.UserRemarkLog;

public interface UserRemarkLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRemarkLog record);

    int insertSelective(UserRemarkLog record);

    UserRemarkLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRemarkLog record);

    int updateByPrimaryKey(UserRemarkLog record);
}