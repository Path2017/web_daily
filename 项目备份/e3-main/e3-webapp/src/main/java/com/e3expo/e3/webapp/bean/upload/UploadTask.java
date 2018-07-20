package com.e3expo.e3.webapp.bean.upload;

import java.io.InputStream;

/**
 * 描述上传任务的类
 */
public class UploadTask {
    /**
     * 上传到本地的全路径
     */
    private final String pathTo;

    /**
     * 上传过来的文件流
     */
    private final InputStream uploadFileIn;

    private final long totalBytes;

    public UploadTask(String pathTo, InputStream uploadFileIn, long totalBytes) {
        this.pathTo = pathTo;
        this.uploadFileIn = uploadFileIn;
        this.totalBytes = totalBytes;
    }

    public String getPathTo() {
        return pathTo;
    }

    public InputStream getUploadFileIn() {
        return uploadFileIn;
    }

    public long getTotalBytes() {
        return totalBytes;
    }
}
