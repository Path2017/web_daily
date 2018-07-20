package com.e3expo.e3.webapp.model;

import java.io.Serializable;

public class RfpId implements Serializable {
    public RfpId(Integer rfpId) {
        this.rfpId = rfpId;
    }

    private Integer rfpId;

    public Integer getRfpId() {
        return rfpId;
    }

    public void setRfpId(Integer rfpId) {
        this.rfpId = rfpId;
    }
}
