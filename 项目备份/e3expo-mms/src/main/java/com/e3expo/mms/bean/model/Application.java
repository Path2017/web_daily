package com.e3expo.mms.bean.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("application")
public class Application {

    private final String dateFormat = "yyyy/MM/dd";

    private int id;
    private byte status;
    private int designId;
    private int applicant;
    private long createTime;
    private long handlingTime;

    private Design design;
    private User applicantUser;
    private String coverTemporaryUrl;

    public Application() {
    }


    public Application(byte status, int designId, int applicant, long createTime) {
        this.status = status;
        this.designId = designId;
        this.applicant = applicant;
        this.createTime = createTime;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getCoverTemporaryUrl() {
        return coverTemporaryUrl;
    }

    public void setCoverTemporaryUrl(String coverTemporaryUrl) {
        this.coverTemporaryUrl = coverTemporaryUrl;
    }

    public Design getDesign() {
        return design;
    }

    public void setDesign(Design design) {
        this.design = design;
    }

    public User getApplicantUser() {
        return applicantUser;
    }

    public void setApplicantUser(User applicantUser) {
        this.applicantUser = applicantUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getDesignId() {
        return designId;
    }

    public void setDesignId(int designId) {
        this.designId = designId;
    }

    public int getApplicant() {
        return applicant;
    }

    public void setApplicant(int applicant) {
        this.applicant = applicant;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getHandlingTime() {
        return handlingTime;
    }

    public void setHandlingTime(long handlingTime) {
        this.handlingTime = handlingTime;
    }

    public Date getCreateTimeDate(){
        return createTime == 0 ? null : new Date(this.createTime);
    }
    public Date getHandlingTimeDate() {
        return handlingTime == 0 ? null : new Date(this.handlingTime);
    }
}