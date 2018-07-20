package com.e3expo.e3.webapp.controller.message;


public class WebappErrorMessage {

    private String error;

    public WebappErrorMessage(String errorMsg) {
        this.error = errorMsg;
    }

    public String getError() {
        return this.error;
    }

}
