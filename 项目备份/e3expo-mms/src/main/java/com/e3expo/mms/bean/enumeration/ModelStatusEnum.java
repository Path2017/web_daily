package com.e3expo.mms.bean.enumeration;

/**
 * 数据库对应的model状态枚举
 */
public enum ModelStatusEnum {
    VALID(1),
    DELETED(-1);

    private byte value;

    ModelStatusEnum(int value) {
        this.value = (byte) value;
    }

    public byte getValue() {
        return value;
    }
}