package com.e3expo.mms.bean.model;

import com.e3expo.mms.bean.param.DesignParam;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.e3expo.mms.bean.enumeration.BooleanStatusEnum.TRUE;
import static com.e3expo.mms.bean.enumeration.ModelStatusEnum.VALID;

@Alias("design")
public class Design implements Serializable {

    private final String dateFormat = "yyyy/MM/dd";

    private int id;
    private long createTime;
    private long modifiedTime;
    private int ownerID;
    private byte professionID;
    private byte structureID;
    private byte exhibitionTypeID;
    private byte openSides;
    private int priceLowerLimit;
    private int priceUpperLimit;
    private int area;
    private String ossKey;
    private int views;
    private int downloads;
    private byte isVisible;
    private byte status;
    private String primitiveName;

    private DesignProfession profession;
    private DesignStructure structure;
    private ExhibitionType exhibitionType;

    private User owner;
    private City city;

    private List<DesignSketch> sketchList;

    private boolean loginUserHasRight;
    private byte applicationStatus;
    private String coverTemporaryUrl;

    private String coverOssKey;

    public Design() {
    }

    public Design(DesignParam param) {
        this.ownerID = param.getOwnerID();
        this.professionID = param.getProfessionID();
        this.structureID = param.getStructureID();
        this.exhibitionTypeID = param.getExhibitionTypeID();
        this.openSides = param.getOpenSides();
        this.priceLowerLimit = param.getPriceLowerLimit();
        this.priceUpperLimit = param.getPriceUpperLimit();
        this.area = param.getArea();
        this.status = VALID.getValue();
        this.isVisible = TRUE.getValue();
        this.primitiveName = param.getPrimitiveName();
    }

    public String getCoverOssKey() {
        return coverOssKey;
    }

    public void setCoverOssKey(String coverOssKey) {
        this.coverOssKey = coverOssKey;
    }

    public User getOwner() {
        return owner;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public byte getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(byte applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getCoverTemporaryUrl() {
        return coverTemporaryUrl;
    }

    public void setCoverTemporaryUrl(String coverTemporaryUrl) {
        this.coverTemporaryUrl = coverTemporaryUrl;
    }

    public String getPrimitiveName() {
        return primitiveName;
    }

    public void setPrimitiveName(String primitiveName) {
        this.primitiveName = primitiveName;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<DesignSketch> getSketchList() {
        return sketchList;
    }

    public void setSketchList(List<DesignSketch> sketchList) {
        this.sketchList = sketchList;
    }

    public DesignProfession getProfession() {
        return profession;
    }

    public void setProfession(DesignProfession profession) {
        this.profession = profession;
    }

    public DesignStructure getStructure() {
        return structure;
    }

    public void setStructure(DesignStructure structure) {
        this.structure = structure;
    }

    public ExhibitionType getExhibitionType() {
        return exhibitionType;
    }

    public void setExhibitionType(ExhibitionType exhibitionType) {
        this.exhibitionType = exhibitionType;
    }

    public boolean isLoginUserHasRight() {
        return loginUserHasRight;
    }

    public void setLoginUserHasRight(boolean loginUserHasRight) {
        this.loginUserHasRight = loginUserHasRight;
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

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public byte getProfessionID() {
        return professionID;
    }

    public void setProfessionID(byte professionID) {
        this.professionID = professionID;
    }

    public byte getStructureID() {
        return structureID;
    }

    public void setStructureID(byte structureID) {
        this.structureID = structureID;
    }

    public byte getExhibitionTypeID() {
        return exhibitionTypeID;
    }

    public void setExhibitionTypeID(byte exhibitionTypeID) {
        this.exhibitionTypeID = exhibitionTypeID;
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getOssKey() {
        return ossKey;
    }

    public void setOssKey(String ossKey) {
        this.ossKey = ossKey;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public byte getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(byte isVisible) {
        this.isVisible = isVisible;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getCreateTimeDate(){
        return new Date(createTime);
    }

    public Date getModifiedTimeDate(){
        return new Date(modifiedTime);
    }
}
