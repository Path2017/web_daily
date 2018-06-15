package com.e3expo.mms.bean.model;

public class FileUploadStatus {

    public static final String SESSION_KEY = "uploading_zip_file_status";

    private final long startTime = System.currentTimeMillis();
    private long totalBytes = -1;
    private long bytesUploaded = 0;

    public FileUploadStatus(long totalBytes) {
        this.totalBytes = totalBytes;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public void setBytesUploaded(long bytesUploaded) {
        this.bytesUploaded = bytesUploaded;
    }

    public long getBytesUploaded() {
        return bytesUploaded;
    }
}
