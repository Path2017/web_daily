package com.e3expo.mms.bean.param;

import com.e3expo.mms.bean.model.User;

import java.util.List;

public class UserListParam extends PageParam<User>{
    private byte cityID;
    private String name;
    private byte roleID;
    private byte isResigned;


    private List<Byte> roleIDList;

    public String getUserName() {
        return this.name;
    }

    public List<Byte> getRoleIDList() {
        return roleIDList;
    }

    public void setRoleIDList(List<Byte> roleIDList) {
        this.roleIDList = roleIDList;
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

    public byte getRoleID() {
        return roleID;
    }

    public void setRoleID(byte roleID) {
        this.roleID = roleID;
    }

    public byte getIsResigned() {
        return isResigned;
    }

    public void setIsResigned(byte isResigned) {
        this.isResigned = isResigned;
    }
}