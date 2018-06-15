package com.e3expo.mms.bean.model;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 城市表对应model
 */
@Alias("city")
public class City implements Serializable{

    public City() {
    }

    public City(byte id, String name, String nickName, byte status) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.status = status;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    private byte id;
    private String name;
    private String nickName;
    private byte status;
    private String bucket;


}