package com.e3expo.e3.model;

import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

import static com.e3expo.e3.validation.group.RfpGroup.*;

@Alias("rfp")
public class Rfp implements Serializable {

    @NotNull(groups = {UpdateDraft.class})
    private Integer id;

    private String rfpNo;

    @NotNull(groups = {Create.class})
    private String companyName;

    @NotNull(groups = {Create.class})
    private String linkmanName;

    @NotNull(groups = {Create.class})
    private String linkmanPhone;

    @NotNull(groups = {Create.class})
    private String linkmanEmail;

    @NotNull(groups = {Create.class})
    private String exhibitorName;

    @NotNull(groups = {Create.class})
    private String companyWebsite;

    @NotNull(groups = {Create.class})
    private String exhibitionName;

    @NotNull(groups = {Create.class})
    private String exhibitionCity;

    @NotNull(groups = {Create.class})
    private String exhibitionHall;

    @NotNull(groups = {Create.class})
    private String boothNo;

    private String exhibitionFloorPlanPath;

    @NotNull(groups = {Create.class})
    private BigDecimal boothWidth;

    @NotNull(groups = {Create.class})
    private BigDecimal boothLength;

    @NotNull(groups = {Create.class})
    private BigDecimal boothLimitHeight;

    @NotNull(groups = {Create.class})
    private BigDecimal budget;

    @NotNull(groups = {Create.class})
    private Long designDeadline;

    @NotNull(groups = {Create.class})
    private Long workingDrawingDeadline;

    private Long createTime;
    private Long updateTime;
    private Integer status;
    private Integer userId;

    private Long wishFinishTime;

    public Long getWishFinishTime() {
        return wishFinishTime;
    }

    public void setWishFinishTime(Long wishFinishTime) {
        this.wishFinishTime = wishFinishTime;
    }

    public Long getDesignDeadline() {
        return designDeadline;
    }

    public void setDesignDeadline(Long designDeadline) {
        this.designDeadline = designDeadline;
    }

    public Long getWorkingDrawingDeadline() {
        return workingDrawingDeadline;
    }

    public void setWorkingDrawingDeadline(Long workingDrawingDeadline) {
        this.workingDrawingDeadline = workingDrawingDeadline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRfpNo() {
        return rfpNo;
    }

    public void setRfpNo(String rfpNo) {
        this.rfpNo = rfpNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getLinkmanEmail() {
        return linkmanEmail;
    }

    public void setLinkmanEmail(String linkmanEmail) {
        this.linkmanEmail = linkmanEmail;
    }

    public String getExhibitorName() {
        return exhibitorName;
    }

    public void setExhibitorName(String exhibitorName) {
        this.exhibitorName = exhibitorName;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getExhibitionName() {
        return exhibitionName;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    public String getExhibitionCity() {
        return exhibitionCity;
    }

    public void setExhibitionCity(String exhibitionCity) {
        this.exhibitionCity = exhibitionCity;
    }

    public String getExhibitionHall() {
        return exhibitionHall;
    }

    public void setExhibitionHall(String exhibitionHall) {
        this.exhibitionHall = exhibitionHall;
    }

    public String getBoothNo() {
        return boothNo;
    }

    public void setBoothNo(String boothNo) {
        this.boothNo = boothNo;
    }

    public String getExhibitionFloorPlanPath() {
        return exhibitionFloorPlanPath;
    }

    public void setExhibitionFloorPlanPath(String exhibitionFloorPlanPath) {
        this.exhibitionFloorPlanPath = exhibitionFloorPlanPath;
    }

    public BigDecimal getBoothWidth() {
        return boothWidth;
    }

    public void setBoothWidth(BigDecimal boothWidth) {
        this.boothWidth = boothWidth;
    }

    public BigDecimal getBoothLength() {
        return boothLength;
    }

    public void setBoothLength(BigDecimal boothLength) {
        this.boothLength = boothLength;
    }

    public BigDecimal getBoothLimitHeight() {
        return boothLimitHeight;
    }

    public void setBoothLimitHeight(BigDecimal boothLimitHeight) {
        this.boothLimitHeight = boothLimitHeight;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}