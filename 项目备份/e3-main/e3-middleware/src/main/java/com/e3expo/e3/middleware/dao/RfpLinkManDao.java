package com.e3expo.e3.middleware.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3expo.e3.middleware.mapper.RfpLinkManMapper;
import com.e3expo.e3.model.RfpLinkMan;
@Repository
public class RfpLinkManDao {
	@Autowired
	private void setSql(SqlSessionTemplate template) {
		this.sqlSessionTemplate = template;
		this.mapper = this.sqlSessionTemplate.getMapper(RfpLinkManMapper.class);
	}

	private RfpLinkManMapper mapper;
	private SqlSessionTemplate sqlSessionTemplate;

	public List<RfpLinkMan> getListByUserId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<RfpLinkMan> list=mapper.getListByUserId(map);
		return list;
	}

	public RfpLinkMan selectByUserId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		RfpLinkMan model=mapper.selectByUserId(map);
		return model;
	}

	public int updateToChoosed(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int num=mapper.updateToChoosed(map);
		return num;
	}

	public int updateToNotChoosed(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int num=mapper.updateToNotChoosed(map);
		return num;
	}

	public int updateToNotValid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int num=mapper.updateToNotValid(map);
		return num;
	}

	public int insertRfpLinkMan(RfpLinkMan model) {
		// TODO Auto-generated method stub
		int num=mapper.insertRfpLinkMan(model);		
		return num;
	}

	public int updateById(RfpLinkMan model) {
		int num=mapper.updateById(model);
		return num;
	}
	
	
}
