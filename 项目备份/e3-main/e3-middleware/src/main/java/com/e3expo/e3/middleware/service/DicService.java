package com.e3expo.e3.middleware.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3expo.e3.middleware.mapper.CityMapper;
import com.e3expo.e3.middleware.mapper.ProvinceMapper;
import com.e3expo.e3.middleware.mapper.UserMapper;

@Service
public class DicService {
	//省的字典表
	private ProvinceMapper provinceMapper;
	//城市的字典表
	private CityMapper cityMapper;
	//设计师类别低级、高级
	//private DesignerConfig designerCofig;
	//前台用户信息表
	private UserMapper userMapper;
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	private void setSqlSessionTemplate(SqlSessionTemplate template) {
		this.sqlSessionTemplate = template;
		this.userMapper = this.sqlSessionTemplate.getMapper(UserMapper.class);
		this.provinceMapper = this.sqlSessionTemplate.getMapper(ProvinceMapper.class);
		this.cityMapper = this.sqlSessionTemplate.getMapper(CityMapper.class);
		//this.designerCofig = this.sqlSessionTemplate.getMapper(DesignerConfig.class);
	}
	
}
