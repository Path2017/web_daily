package com.e3expo.e3.model;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("designer")
public class Designer implements Serializable {

    private Integer id;

    private Integer userId;

    private Integer designerType;

    private Long companyCreateTime;

    private String workingYears;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getDesignerType() {
        return designerType;
    }


    public void setDesignerType(Integer designerType) {
        this.designerType = designerType;
    }

    public Long getCompanyCreateTime() {
        return companyCreateTime;
    }


    public void setCompanyCreateTime(Long companyCreateTime) {
        this.companyCreateTime = companyCreateTime;
    }

    public String getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }
}