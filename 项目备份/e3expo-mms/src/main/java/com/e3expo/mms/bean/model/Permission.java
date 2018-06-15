package com.e3expo.mms.bean.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("permission")
public class Permission implements Serializable {

	
	
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *  私有数据和私有方法
	 */
	private int id;
	private String name;
	private String description;
	
	private static final long serialVersionUID = 7686486183292848405L;

}
