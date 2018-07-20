package com.e3expo.e3.enumration;

public enum EnumOrderStatus {

    ORDER_NORMAL(0, "正常"),
    ORDER_CANCEL(1, "已取消"),
    ORDER_TERMINATE(2, "已终止"),
    ORDER_COMPLETE(3, "已完成");
    private int value;
    private String name;

    EnumOrderStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static EnumOrderStatus getType(String name) {
        for (EnumOrderStatus type : EnumOrderStatus.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return null;
    }
}
