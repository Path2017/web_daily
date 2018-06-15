package com.e3expo.mms.bean.model;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

import static com.e3expo.mms.bean.enumeration.ModelStatusEnum.*;

/**
 * 设计图行业字典model
 */
@Alias("designSketch")
public class DesignSketch implements Serializable {

    private int id;
    private long createTime;
    private long modifiedTime;
    private int designID;
    private String ossKey;
    private byte isCover;
    private byte status;
    private String primitiveName;


    private String temporaryUrl;

    public DesignSketch() {
    }

    public DesignSketch(int designID, String ossKey) {
        this.designID = designID;
        this.ossKey = ossKey;
        this.status = VALID.getValue();
    }

    public String getTemporaryUrl() {
        return temporaryUrl;
    }

    public void setTemporaryUrl(String temporaryUrl) {
        this.temporaryUrl = temporaryUrl;
    }

    public String getPrimitiveName() {
        return primitiveName;
    }

    public void setPrimitiveName(String primitiveName) {
        this.primitiveName = primitiveName;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDesignID() {
        return designID;
    }

    public void setDesignID(int designID) {
        this.designID = designID;
    }

    public String getOssKey() {
        return ossKey;
    }

    public void setOssKey(String ossKey) {
        this.ossKey = ossKey;
    }

    public void setIsCover(byte isCover) {
        this.isCover = isCover;
    }

    public byte getIsCover() {
        return isCover;
    }
}