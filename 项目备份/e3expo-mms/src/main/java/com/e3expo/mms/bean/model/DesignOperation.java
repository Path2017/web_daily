package com.e3expo.mms.bean.model;

import org.apache.ibatis.type.Alias;

@Alias("designOperation")
public class DesignOperation {

    private byte id;
    private byte status;
    private String name;
    private String fullName;

    public DesignOperation() {
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getName() {
        return name;
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
}