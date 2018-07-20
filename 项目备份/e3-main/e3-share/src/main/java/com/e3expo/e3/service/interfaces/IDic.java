package com.e3expo.e3.service.interfaces;

import java.util.List;

import com.e3expo.e3.model.City;
import com.e3expo.e3.model.Country;
import com.e3expo.e3.model.DesignerWorkingYears;
import com.e3expo.e3.model.Province;
import com.e3expo.e3.model.UserFileConfig;

public interface IDic {
	/**
	 * 获取国家列表
	 * 
	 * @return
	 */
	List<Country> getCountries();

	/**
	 * 根据国家编号获取省份列表
	 * 
	 * @param record
	 * @return
	 */
	List<Province> getProvinceByCountryId(Province record);

	/**
	 * 根据省份编号，获取城市列表
	 * 
	 * @param record
	 * @return
	 */
	List<City> getCityByProvinceId(City record);
	/**
	 * 获取工作年份
	 * @return
	 */
	List<DesignerWorkingYears> getWorkYear();
	/**
	 * 获取用户上传文件的的具体说明信息，包括文件大小，文件类型等
	 * @return
	 */
	List<UserFileConfig> getUserFileCOnfigs();
	/**
	 * 根据上传文件的key获取用户文件的相关配置信息
	 * @param key
	 * @return
	 */
	UserFileConfig getUserFileConfigByName(String key);
}
