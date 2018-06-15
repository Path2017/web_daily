package com.e3expo.mms.bean.param;

/**
 * 用户表单参数对应的类
 */
public class UserParam {

    private byte cityID;
    private byte roleID;
    private String name;
    private String phoneNumber;
    private String phoneAreaCode;
    private String email;
    private String password;
    private byte isResigned;

    private int userID;
    private long modifiedTime;

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public byte getCityID() {
        return cityID;
    }

    public void setCityID(byte cityID) {
        this.cityID = cityID;
    }

    public byte getRoleID() {
        return roleID;
    }

    public void setRoleID(byte roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getIsResigned() {
        return isResigned;
    }

    public void setIsResigned(byte isResigned) {
        this.isResigned = isResigned;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

}