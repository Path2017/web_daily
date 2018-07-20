package com.e3expo.e3.middleware.mapper;

import java.util.List;
import java.util.Map;

import com.e3expo.e3.model.RfpLinkMan;

public interface RfpLinkManMapper {
	List<RfpLinkMan> getListByUserId(Map<String, Object> map);

	RfpLinkMan selectByUserId(Map<String, Object> map);

	int updateToChoosed(Map<String, Object> map);

	int updateToNotChoosed(Map<String, Object> map);

	int updateToNotValid(Map<String, Object> map);

	int insertRfpLinkMan(RfpLinkMan model);
	int updateById(RfpLinkMan model);

}
