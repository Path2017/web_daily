package com.e3expo.e3.webapp.bean.model;

import com.e3expo.e3.webapp.validation.group.VeriCodeGroup;

import javax.validation.constraints.NotNull;

public class VeriCodeModel {

    @NotNull(groups = {VeriCodeGroup.CheckVeriCode.class, VeriCodeGroup.PostVeriCode.class })
    private String mobile;
    @NotNull(groups = {VeriCodeGroup.CheckVeriCode.class})
    private String veriCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVeriCode() {
        return veriCode;
    }

    public void setVeriCode(String veriCode) {
        this.veriCode = veriCode;
    }
}
