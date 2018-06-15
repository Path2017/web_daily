package com.e3expo.mms.bean.model;

import com.e3expo.mms.bean.param.UserParam;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("user")
public class User implements Serializable {

	
	public User() {
		super();
	}



	public User(String phoneNumber,
				byte    cityID,
				String name,
				String phoneAreaCode,
				String email,
				byte   isResigned,
				String password,
				long   createTime,
				long   modifiedTime) {
		this.phoneNumber = phoneNumber;
		this.cityID = cityID;
		this.name = name;
		this.phoneAreaCode = phoneAreaCode;
		this.email = email;
		this.isResigned = isResigned;
		this.password = password;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}

	public User(UserParam param) {
		this.phoneNumber = param.getPhoneNumber();
		this.cityID = param.getCityID();
		this.name = param.getName();
		this.phoneAreaCode = param.getPhoneAreaCode();
		this.email = param.getEmail();
		this.isResigned = param.getIsResigned();
		this.password = param.getPassword();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public byte getCityID() {
		return cityID;
	}

	public void setCityID(byte cityID) {
		this.cityID = cityID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}

	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getIsResigned() {
		return isResigned;
	}

	public void setIsResigned(byte isResigned) {
		this.isResigned = isResigned;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	/**
	 *  私有数据和私有方法
	 */
	
	private int id;
	private String phoneNumber;
	private byte cityID;
	private String name;
	private String phoneAreaCode;
	private String email;
	private byte isResigned;
	private String password;
	private long createTime;
	private long modifiedTime;

	private Role role;
	private City city;
	

	private static final long serialVersionUID = -8801105665591690927L;
	
}
