package com.e3expo.e3.middleware.common.redis.expiremessage;

import java.util.EnumSet;

/**
 * enum的name属性命名不能包含英文冒号 ‘:’，path属性就是指定task的Class的path，
 * expire是超时的时间，单位是秒
 */
public enum OrderExpireMessageTypeEnum {

    ORDER_END_BIDDING_TASK("order-bidding-expire-message", "com.e3expo.e3.middleware.common.redis.expiremessage.task.OrderEndBiddingTask", 60 * 60 * 48),
    ORDER_END_PAY_FOR_DESIGN_TASK("order-pay-for-design-expire-message", "com.e3expo.e3.middleware.common.redis.expiremessage.task.OrderEndPayForDesignTask", 60 * 60 * 72),
    ORDER_END_PAY_FOR_WORKING_DRAWING_TASK("order-pay-for-working-drawing-expire-message", "com.e3expo.e3.middleware.common.redis.expiremessage.task.OrderEndPayForWorkingDrawingTask", 60 * 60 * 72);

    private final String name;
    private final String path;
    private final int expire;

    OrderExpireMessageTypeEnum(String name, String path, int expire) {
        this.name = name;
        this.path = path;
        this.expire = expire;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getExpire() {
        return expire;
    }

    public static OrderExpireMessageTypeEnum getByName(String name) {
        for (OrderExpireMessageTypeEnum anEnum : EnumSet.allOf(OrderExpireMessageTypeEnum.class)) {
            if (anEnum.name.equals(name)) {
                return anEnum;
            }
        }
        return null;
    }
}
