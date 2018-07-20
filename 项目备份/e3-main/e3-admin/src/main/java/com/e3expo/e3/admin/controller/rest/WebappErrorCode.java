package com.e3expo.e3.admin.controller.rest;

import com.e3expo.e3.exceptions.BaseErrorCode;

public class WebappErrorCode implements BaseErrorCode {

    private int errorCode;
    private String desc;

    public WebappErrorCode(int errorCode, String desc) {
        this.errorCode = errorCode;
        this.desc = desc;
    }

    @Override
    public int getValue() {
        return this.errorCode;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

}
