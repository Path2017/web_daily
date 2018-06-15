package com.e3expo.mms.bean.enumeration;

/**
 * 对于设计图操作的枚举
 */
public enum DesignOperationEnum {
    VIEW_DETAILS(1, "查看"),
    MODIFY(2, "编辑"),
    DOWNLOAD_SUCCESSFULLY(3, "成功下载");
    private byte id;
    private String fullName;

    DesignOperationEnum(int id, String fullName) {
        this.id = (byte) id;
        this.fullName = fullName;
    }

    public byte getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}