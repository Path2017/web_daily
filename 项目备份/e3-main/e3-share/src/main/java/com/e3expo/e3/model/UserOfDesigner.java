package com.e3expo.e3.model;

import java.util.*;
import java.io.Serializable;

import org.apache.ibatis.type.Alias;
/**
 * 该类用于封装designer的全部用户信息
 * @author lizy
 *
 */

@Alias("userOfDesigner")
public class UserOfDesigner implements Serializable {
	private User user;
	private Designer designer;
	private City city;
	private Province province;
	private List<UserInfoFile> userInfoFileList;
	private List<UserAuditLog> userAuditLogList;

	public User getUser() {
		return user;
	}

	public void setUser(User User) {
		this.user = User;
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

	public List<UserInfoFile> getUserInfoFileList() {
		return userInfoFileList;
	}

	public void setUserInfoFileList(List<UserInfoFile> userInfoFileList) {
		this.userInfoFileList = userInfoFileList;
	}

	public List<UserAuditLog> getUserAuditLogList() {
		return userAuditLogList;
	}

	public void setUserAuditLogList(List<UserAuditLog> userAuditLogList) {
		this.userAuditLogList = userAuditLogList;
	}

	public Designer getDesigner() {
		return designer;
	}

	public void setDesigner(Designer designer) {
		this.designer = designer;
	}
	
}
