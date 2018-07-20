package com.e3expo.e3.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("userModel")
public class UserModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3336038804042043922L;


	private String mobile;
	
	
	private String password;


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
