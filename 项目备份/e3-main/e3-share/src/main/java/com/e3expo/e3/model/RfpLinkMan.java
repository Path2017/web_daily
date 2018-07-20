package com.e3expo.e3.model;

public class RfpLinkMan {
private Integer id;
private String linkName;
private String linkMobile;
private String linkEmail;
private Integer isValid;
private Integer isChoosed;
private Integer userId;
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getLinkName() {
	return linkName;
}
public void setLinkName(String linkName) {
	this.linkName = linkName;
}
public String getLinkMobile() {
	return linkMobile;
}
public void setLinkMobile(String linkMobile) {
	this.linkMobile = linkMobile;
}
public String getLinkEmail() {
	return linkEmail;
}
public void setLinkEmail(String string) {
	this.linkEmail = string;
}
public Integer getIsValid() {
	return isValid;
}
public void setIsValid(Integer isValid) {
	this.isValid = isValid;
}
public Integer getIsChoosed() {
	return isChoosed;
}
public void setIsChoosed(Integer isChoosed) {
	this.isChoosed = isChoosed;
}




}
