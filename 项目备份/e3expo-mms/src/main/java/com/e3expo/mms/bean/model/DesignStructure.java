package com.e3expo.mms.bean.model;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;


/**
 * 设计图行业字典model
 */
@Alias("designStructure")
public class DesignStructure implements Serializable {

    private byte id;
    private byte status;
    private String name;

    public DesignStructure(byte id, byte status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
    }

    public DesignStructure() {
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
}