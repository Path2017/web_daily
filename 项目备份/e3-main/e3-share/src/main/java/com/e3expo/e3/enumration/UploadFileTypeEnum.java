package com.e3expo.e3.enumration;

/**
 * 上传文件格式类型
 */
public enum UploadFileTypeEnum {
    JPG(".jpg"),PNG(".png"),PDF(".pdf"),JPEG(".jpeg"),
    RAR(".rar"),AI(".ai"),ZIP(".zip");

    UploadFileTypeEnum(String suffix) {
        this.suffix = suffix;
    }

    private final String suffix;

    public String getSuffix() {
        return suffix;
    }

    @Override
    public String toString() {
        return suffix;
    }
}
