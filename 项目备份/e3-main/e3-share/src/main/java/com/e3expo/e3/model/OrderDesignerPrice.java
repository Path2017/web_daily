package com.e3expo.e3.model;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;

@Alias("orderDesignerPrice")
public class OrderDesignerPrice implements Serializable {

    private Integer id;
    private String nodeId;
    private Integer designerId;
    private BigDecimal price;
    private Integer orderId;
    private Integer type;
    private Integer updateNum;

    public OrderDesignerPrice() {
    }

    public OrderDesignerPrice(String nodeId, Integer designerId, BigDecimal price, Integer orderId, Integer type, Integer updateNum) {
        this.nodeId = nodeId;
        this.designerId = designerId;
        this.price = price;
        this.orderId = orderId;
        this.type = type;
        this.updateNum = updateNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUpdateNum() {
        return updateNum;
    }

    public void setUpdateNum(Integer updateNum) {
        this.updateNum = updateNum;
    }
}
