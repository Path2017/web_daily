package com.e3expo.mms.bean.param;

import com.e3expo.mms.bean.model.Application;

/**
 * 我的下载列表页查询分页参数
 */
public class DownloadListParam extends PageParam<Application>{
    private byte cityID;
    private byte professionID;
    private byte exhibitionTypeID;
    private byte structureID;
    private byte openSides;
    private int priceLowerLimit;
    private int priceUpperLimit;
    private int areaLowerLimit;
    private int areaUpperLimit;

    private byte approved;

//    private byte invisible;
//    private byte createdByMyself;
    private byte downloadable;

    private int ownerID;

    private byte orderTypeValue;

    private int designID;
    private int userId;

    public DownloadListParam() {
    }

    public byte getApproved() {
        return approved;
    }

    public void setApproved(byte approved) {
        this.approved = approved;
    }

    public byte getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(byte downloadable) {
        this.downloadable = downloadable;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public byte getOrderTypeValue() {
        return orderTypeValue;
    }

    public void setOrderTypeValue(byte orderTypeValue) {
        this.orderTypeValue = orderTypeValue;
    }

    public byte getCityID() {
        return cityID;
    }

    public void setCityID(byte cityID) {
        this.cityID = cityID;
    }

    public byte getProfessionID() {
        return professionID;
    }

    public void setProfessionID(byte professionID) {
        this.professionID = professionID;
    }

    public byte getExhibitionTypeID() {
        return exhibitionTypeID;
    }

    public void setExhibitionTypeID(byte exhibitionTypeID) {
        this.exhibitionTypeID = exhibitionTypeID;
    }

    public byte getStructureID() {
        return structureID;
    }

    public void setStructureID(byte structureID) {
        this.structureID = structureID;
    }

    public byte getOpenSides() {
        return openSides;
    }

    public void setOpenSides(byte openSides) {
        this.openSides = openSides;
    }

    public int getPriceLowerLimit() {
        return priceLowerLimit;
    }

    public void setPriceLowerLimit(int priceLowerLimit) {
        this.priceLowerLimit = priceLowerLimit;
    }

    public int getPriceUpperLimit() {
        return priceUpperLimit;
    }

    public void setPriceUpperLimit(int priceUpperLimit) {
        this.priceUpperLimit = priceUpperLimit;
    }

    public int getAreaLowerLimit() {
        return areaLowerLimit;
    }

    public void setAreaLowerLimit(int areaLowerLimit) {
        this.areaLowerLimit = areaLowerLimit;
    }

    public int getAreaUpperLimit() {
        return areaUpperLimit;
    }

    public void setAreaUpperLimit(int areaUpperLimit) {
        this.areaUpperLimit = areaUpperLimit;
    }

    public int getDesignID() {
        return designID;
    }

    public void setDesignID(int designID) {
        this.designID = designID;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}