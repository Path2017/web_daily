package com.e3expo.mms.bean.model;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("role")
public class Role implements Serializable {

	
	public byte getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public void setId(byte id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 *  私有数据和私有方法
	 */
	private byte id;
	private String name;
	private String fullName;
	
	private static final long serialVersionUID = 5730807892061092496L;

}
