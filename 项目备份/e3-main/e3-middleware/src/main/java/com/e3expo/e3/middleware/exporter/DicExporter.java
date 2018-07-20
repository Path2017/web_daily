package com.e3expo.e3.middleware.exporter;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e3expo.e3.middleware.mapper.CityMapper;
import com.e3expo.e3.middleware.mapper.CountryMapper;
import com.e3expo.e3.middleware.mapper.DesignerWorkingYearsMapper;
import com.e3expo.e3.middleware.mapper.ProvinceMapper;
import com.e3expo.e3.middleware.mapper.UserFileConfigMapper;
import com.e3expo.e3.model.City;
import com.e3expo.e3.model.Country;
import com.e3expo.e3.model.DesignerWorkingYears;
import com.e3expo.e3.model.Province;
import com.e3expo.e3.model.UserFileConfig;
import com.e3expo.e3.service.interfaces.IDic;

@Component
public class DicExporter implements IDic {
	// 国家的字典表信息
	private CountryMapper countryMapper;
	// 省的字典表
	private ProvinceMapper provinceMapper;
	// 城市的字典表
	private CityMapper cityMapper;
	//获取设计师工作年限表
	private DesignerWorkingYearsMapper designerWorkingYearsMapper;
	//上传文件的配置信息
	private UserFileConfigMapper userFileConfigMapper;
	//
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	private void setSqlSessionTemplate(SqlSessionTemplate template) {
		this.sqlSessionTemplate = template;
		this.countryMapper = this.sqlSessionTemplate.getMapper(CountryMapper.class);
		this.provinceMapper = this.sqlSessionTemplate.getMapper(ProvinceMapper.class);
		this.designerWorkingYearsMapper = this.sqlSessionTemplate.getMapper(DesignerWorkingYearsMapper.class);
		this.cityMapper = this.sqlSessionTemplate.getMapper(CityMapper.class);
		this.userFileConfigMapper=this.sqlSessionTemplate.getMapper(UserFileConfigMapper.class);
	}

	@Override
	public List<Country> getCountries() {
		return this.countryMapper.getCountries();
	}

	@Override
	public List<Province> getProvinceByCountryId(Province record) {
		return this.provinceMapper.getProvincesByCountryId(record);
	}

	@Override
	public List<City> getCityByProvinceId(City record) {
		return this.cityMapper.getCityByProvinceId(record);
	}

	@Override
	public List<DesignerWorkingYears> getWorkYear() {
		return this.designerWorkingYearsMapper.getList();
	}

	@Override
	public List<UserFileConfig> getUserFileCOnfigs() {
		return this.userFileConfigMapper.getList();
	}

	@Override
	public UserFileConfig getUserFileConfigByName(String key) {
		return this.userFileConfigMapper.getByName(key);
	}
	

}
