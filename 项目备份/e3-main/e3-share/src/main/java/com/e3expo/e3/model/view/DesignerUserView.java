package com.e3expo.e3.model.view;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.e3expo.e3.model.City;
import com.e3expo.e3.model.Country;
import com.e3expo.e3.model.Province;
import com.e3expo.e3.model.User;
import com.e3expo.e3.model.UserAuditLog;
import com.e3expo.e3.model.UserInfoFile;
import com.e3expo.e3.model.UserRemarkLog;
@Alias("designerUserView")
public class DesignerUserView extends User implements Serializable {
	private List<UserInfoFile> images;
	private Country country;
	private Province province;
	private City city;
	private List<UserRemarkLog> userRemarkLogs;
    
	public List<UserInfoFile> getImages() {
		return images;
	}

	public void setImages(List<UserInfoFile> images) {
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

	public List<UserRemarkLog> getUserRemarkLogs() {
		return userRemarkLogs;
	}

	public void setUserRemarkLogs(List<UserRemarkLog> userRemarkLogs) {
		this.userRemarkLogs = userRemarkLogs;
	}


}
