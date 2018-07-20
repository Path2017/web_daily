package com.e3expo.e3.webapp.controller.message;

public class ErrorMessage {
    private String msg;
    private int errorCode;

    public ErrorMessage (int errorCode, String msg) {
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
