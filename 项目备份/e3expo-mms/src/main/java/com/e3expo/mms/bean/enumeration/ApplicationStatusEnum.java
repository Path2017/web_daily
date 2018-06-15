package com.e3expo.mms.bean.enumeration;

/**
 * 下载申请状态枚举
 */
public enum ApplicationStatusEnum {
    APPLYING(0, "待审核"),
    APPROVED(1, "通过"),
    NOT_APPROVED(-1, "未通过"),
    DEFAULT_APPROVED(2, "默认通过"),
    DELETED(3, "已删除");

    private byte value;
    // 审批状态，0，申请中；1，审批通过；-1，审批未通过
    private String name;

    ApplicationStatusEnum(int value, String name) {
        this.value = (byte) value;
        this.name = name;
    }

    public byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}