package com.e3expo.e3.model.form;

import com.e3expo.e3.validation.group.DesignerPriceConfigFormGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class DesignerPriceConfigForm implements Serializable{

    @NotNull(groups = {DesignerPriceConfigFormGroup.SetPriceConfigAndBid.class})
    private Integer orderId;
    private Integer designerId;
    @NotNull(groups = {DesignerPriceConfigFormGroup.SetPriceConfigAndBid.class})
    private BigDecimal designPrice;
    @NotNull(groups = {DesignerPriceConfigFormGroup.SetPriceConfigAndBid.class})
    private BigDecimal modifyDesignPrice;
    @NotNull(groups = {DesignerPriceConfigFormGroup.SetPriceConfigAndBid.class})
    private BigDecimal workingDrawingPrice;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }
}
