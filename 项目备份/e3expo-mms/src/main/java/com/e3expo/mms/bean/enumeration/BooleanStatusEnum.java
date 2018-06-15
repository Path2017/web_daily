package com.e3expo.mms.bean.enumeration;

/**
 * 布尔型的byte对应状态枚举
 */
public enum BooleanStatusEnum {
    TRUE(1),FALSE(0);

    private byte value;

    BooleanStatusEnum(int value) {
        this.value = (byte) value;
    }

    public byte getValue() {
        return this.value;
    }
}