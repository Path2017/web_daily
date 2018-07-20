package com.e3expo.e3.webapp.bean.upload.client.event;

public class SaveProgressInRedisListener implements ProgressListener {

    private long totalBytes = -1;
    private long bytesWritten = 0;
    private boolean succeed = false;

    private String pathTo;

    public SaveProgressInRedisListener(long totalBytes, String pathTo) {
        this.totalBytes = totalBytes;
        this.pathTo = pathTo;
    }

    @Override
    public void progressChanged(ProgressEvent progressEvent) {
        long bytes = progressEvent.getBytes();
        ProgressEventType eventType = progressEvent.getEventType();
        switch (eventType) {
            case TRANSFER_STARTED_EVENT:
                System.out.println("Start to upload......");
                break;
            case REQUEST_BYTE_TRANSFER_EVENT:
                this.bytesWritten += bytes;
                if (this.totalBytes != -1) {
                    int percent = (int)(this.bytesWritten * 100.0 / this.totalBytes);
                    System.out.println(bytes + " bytes have been written at this time, upload progress: " + percent + "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
                } else {
                    System.out.println(bytes + " bytes have been written at this time, upload ratio: unknown" + "(" + this.bytesWritten + "/...)");
                }
                break;
            case TRANSFER_COMPLETED_EVENT:
                this.succeed = true;
                System.out.println("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
                break;
            case TRANSFER_FAILED_EVENT:
                // 修改字段
                System.out.println("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
                break;
            default:
                break;
        }
    }
}
