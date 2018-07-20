package com.e3expo.e3.middleware.mapper;

import java.util.List;

import com.e3expo.e3.model.UserFileConfig;

public interface UserFileConfigMapper {

	List<UserFileConfig> getList();

	UserFileConfig getByName(String key);
}