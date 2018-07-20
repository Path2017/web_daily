package com.e3expo.e3.model.view;

import org.apache.ibatis.type.Alias;

import com.e3expo.e3.model.User;

@Alias("userView")
public class UserView extends User {
	private String countryName;
	private String provinceName;
	private String cityName;
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
