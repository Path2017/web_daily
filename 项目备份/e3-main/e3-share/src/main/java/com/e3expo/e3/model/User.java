package com.e3expo.e3.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

import static com.e3expo.e3.validation.group.UserGroup.*;

@Alias("user")
public class User implements Serializable {


    private Integer id;

    private String jobNumber;

    @NotNull(groups = {RegisterExhibitionCompany.class, RegisterDesigner.class})
    private String mobile;

    @NotNull(groups = {RegisterExhibitionCompany.class, RegisterDesigner.class})
    private String password;

    @NotNull(groups = {RegisterExhibitionCompany.class, RegisterDesigner.class})
    @Email(groups = {RegisterExhibitionCompany.class, RegisterDesigner.class})
    private String email;

    private Long createTime;

    private Integer status;
    
    private String statusRemark;

    private Integer isValid;

    private Long updateTime;

    private Integer userType;

    @NotNull(groups = {RegisterDesigner.class})
    private String qq;

    @NotNull(groups = {RegisterDesigner.class, RegisterExhibitionCompany.class})
    private Integer countryId;

    @NotNull(groups = {RegisterDesigner.class, RegisterExhibitionCompany.class})
    private Integer provinceId;

    @NotNull(groups = {RegisterDesigner.class, RegisterExhibitionCompany.class})
    private Integer cityId;

    @NotNull(groups = {RegisterDesigner.class})
    private String bankAccount;

    @NotNull(groups = {RegisterDesigner.class})
    private String bankUserName;

    @NotNull(groups = {RegisterDesigner.class})
    private String bankName;

    @NotNull(groups = {RegisterDesigner.class})
    private String bankBranchName;

    @NotNull(groups = {RegisterExhibitionCompany.class})
    private String companyName;

    /**
     * 设计师的姓名
     */
    @NotNull(groups = {RegisterDesigner.class})
    private String name;

    private String logoUrl;

    private Integer systemTag;
    private String auditRemark;
    @NotNull(groups = {RegisterDesigner.class})
    private String workingYears;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getSystemTag() {
        return systemTag;
    }

    public void setSystemTag(Integer systemTag) {
        this.systemTag = systemTag;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }

	public String getStatusRemark() {
		return statusRemark;
	}

	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}
    
}