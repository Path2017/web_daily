package com.e3expo.mms.bean.dao;



import com.e3expo.mms.bean.mapper.CityMapper;
import com.e3expo.mms.bean.model.City;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CityDao {

	/**
	 * 查询所有城市信息
	 *
	 * @return 所有城市列表
	 */
	public List<City> getAllCities() {
		return mapper.getAllCities();
	}


	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
		this.mapper = this.sqlSession.getMapper(CityMapper.class);
	}
	
	private CityMapper mapper;
	private SqlSessionTemplate sqlSession;


}
