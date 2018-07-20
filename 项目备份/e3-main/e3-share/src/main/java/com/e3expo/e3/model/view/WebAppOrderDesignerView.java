package com.e3expo.e3.model.view;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;

@Alias("webAppOrderDesignerView")
public class WebAppOrderDesignerView implements Serializable{

    private Integer designerOrderId;
    private Integer userId;
    private String designerName;
    private String workingYears;
    private String city;
    private String mobile;
    private BigDecimal designPrice;
    private BigDecimal modifyDesignPrice;
    private BigDecimal workingDrawingPrice;


    public Integer getDesignerOrderId() {
        return designerOrderId;
    }

    public void setDesignerOrderId(Integer designerOrderId) {
        this.designerOrderId = designerOrderId;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getDesignPrice() {
        return designPrice;
    }

    public void setDesignPrice(BigDecimal designPrice) {
        this.designPrice = designPrice;
    }

    public BigDecimal getModifyDesignPrice() {
        return modifyDesignPrice;
    }

    public void setModifyDesignPrice(BigDecimal modifyDesignPrice) {
        this.modifyDesignPrice = modifyDesignPrice;
    }

    public BigDecimal getWorkingDrawingPrice() {
        return workingDrawingPrice;
    }

    public void setWorkingDrawingPrice(BigDecimal workingDrawingPrice) {
        this.workingDrawingPrice = workingDrawingPrice;
    }
}
