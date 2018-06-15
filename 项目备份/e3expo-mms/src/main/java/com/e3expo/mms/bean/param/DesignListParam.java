package com.e3expo.mms.bean.param;

import com.e3expo.mms.bean.model.Design;

/**
 * 设计图列表页查询分页参数
 */
public class DesignListParam extends PageParam<Design>{
    private byte cityID;
    private byte professionID;
    private byte exhibitionTypeID;
    private byte structureID;
    private byte openSides;
    private int priceLowerLimit;
    private int priceUpperLimit;
    private int areaLowerLimit;
    private int areaUpperLimit;
    // 可能这个字段为空
    private byte invisible;
    private byte createdByMyself;

    private int ownerID;

    private byte orderTypeValue;

    private int designID;

    public DesignListParam() {
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

    public byte getInvisible() {
        return invisible;
    }

    public void setInvisible(byte invisible) {
        this.invisible = invisible;
    }

    public byte getCreatedByMyself() {
        return createdByMyself;
    }

    public void setCreatedByMyself(byte createdByMyself) {
        this.createdByMyself = createdByMyself;
    }

    public int getDesignID() {
        return designID;
    }

    public void setDesignID(int designID) {
        this.designID = designID;
    }
}