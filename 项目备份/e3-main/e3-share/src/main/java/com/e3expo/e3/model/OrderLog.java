package com.e3expo.e3.model;

public class OrderLog {

    private Integer id;
    private Integer operation;
    private Integer userId;
    private Integer orderId;
    private Long createTime;
    private String remark;

    public OrderLog() {
    }

    public OrderLog(Integer operation, Integer userId, Integer orderId, Long createTime, String remark) {
        this.operation = operation;
        this.userId = userId;
        this.orderId = orderId;
        this.createTime = createTime;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
