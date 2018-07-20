package com.e3expo.e3.model.view;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.e3expo.e3.model.City;
import com.e3expo.e3.model.Country;
import com.e3expo.e3.model.Province;
import com.e3expo.e3.model.UserAudit;
import com.e3expo.e3.model.UserAuditFile;
import com.e3expo.e3.model.UserAuditRemarkLog;
import com.e3expo.e3.model.UserInfoFile;
import com.e3expo.e3.model.UserRemarkLog;
@Alias("userAuditDetailView")
public class UserAuditDetailView extends UserAudit{
	private List<UserAuditFile> images;
	private Country country;
	private Province province;
	private City city;
	private List<UserAuditRemarkLog> userAuditRemarkLogs;
	public List<UserAuditFile> getImages() {
		return images;
	}
	public void setImages(List<UserAuditFile> images) {
		this.images = images;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public List<UserAuditRemarkLog> getUserAuditRemarkLogs() {
		return userAuditRemarkLogs;
	}
	public void setUserAuditRemarkLogs(List<UserAuditRemarkLog> userAuditRemarkLogs) {
		this.userAuditRemarkLogs = userAuditRemarkLogs;
	}
	
}
