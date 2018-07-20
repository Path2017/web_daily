package com.e3expo.e3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;
@Alias("userFileConfig")
public class UserFileConfig implements Serializable {
	private Integer id;

	private String name;

	private Integer isValid;

	private Integer userType;

	private Integer lower;

	private Integer upper;

	private String remark;

	private Integer fileLimit;

	private String fileType;
	private List<String> fileTypeArr;

	public List<String> getFileTypeArr() {
		if (this.getFileType() == null || this.getFileType().length() == 0) {
			return new ArrayList<String>();
		} else {
			String[] str = this.getFileType().split(",");
			return java.util.Arrays.asList(str);
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getLower() {
		return lower;
	}

	public void setLower(Integer lower) {
		this.lower = lower;
	}

	public Integer getUpper() {
		return upper;
	}

	public void setUpper(Integer upper) {
		this.upper = upper;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getFileLimit() {
		return fileLimit;
	}

	public void setFileLimit(Integer fileLimit) {
		this.fileLimit = fileLimit;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType == null ? null : fileType.trim();
	}
}