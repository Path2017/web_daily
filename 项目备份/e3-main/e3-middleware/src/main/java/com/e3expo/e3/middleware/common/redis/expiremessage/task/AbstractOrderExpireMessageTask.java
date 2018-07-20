package com.e3expo.e3.middleware.common.redis.expiremessage.task;

public abstract class AbstractOrderExpireMessageTask implements Runnable{

    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
