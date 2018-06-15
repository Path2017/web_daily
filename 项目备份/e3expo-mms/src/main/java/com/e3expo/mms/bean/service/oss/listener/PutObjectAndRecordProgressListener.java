package com.e3expo.mms.bean.service.oss.listener;

import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.e3expo.mms.bean.dao.DesignDao;
import com.e3expo.mms.bean.model.FileUploadStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("putObjectAndRecordProgressListener")
@Scope("prototype")
public class PutObjectAndRecordProgressListener implements ProgressListener {
    private long bytesWritten = 0;
    private long totalBytes = -1;
    private boolean succeed = false;


    private int designId;
    private String ossKey;

    private FileUploadStatus uploadStatus;

    public long getTotalBytes() {
        return totalBytes;
    }

    public void setTotalBytes(long totalBytes) {
        this.totalBytes = totalBytes;
    }

    public String getOssKey() {
        return ossKey;
    }

    public void setOssKey(String ossKey) {
        this.ossKey = ossKey;
    }

    public int getDesignId() {
        return designId;
    }

    public void setDesignId(int designId) {
        this.designId = designId;
    }

    @Autowired
    private DesignDao designDao;

    public PutObjectAndRecordProgressListener() {
    }

    public PutObjectAndRecordProgressListener(long totalBytes) {
        this.totalBytes = totalBytes;
    }
    public PutObjectAndRecordProgressListener(long totalBytes, int designId, String ossKey) {
        this.totalBytes = totalBytes;
        this.designId = designId;
        this.ossKey = ossKey;
    }

    @Override
    public void progressChanged(ProgressEvent progressEvent) {
        long bytes = progressEvent.getBytes();
        ProgressEventType eventType = progressEvent.getEventType();
        switch (eventType) {
            case TRANSFER_STARTED_EVENT:
                System.out.println("Start to upload......");
//                this.totalBytes = bytes;
//                System.out.println(this.totalBytes + " bytes in total will be uploaded to OSS");
//                ZipFileUploadStatus status = (ZipFileUploadStatus) SecurityUtils.getSubject().getSession().getAttribute(ZipFileUploadStatus.SESSION_KEY);
                FileUploadStatus status = new FileUploadStatus(totalBytes);
                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute(FileUploadStatus.SESSION_KEY, status);
                this.uploadStatus = status;
                break;
            case REQUEST_CONTENT_LENGTH_EVENT:
//                this.totalBytes = bytes;
                System.out.println(this.totalBytes + " bytes in total will be uploaded to OSS");
////                ZipFileUploadStatus status = (ZipFileUploadStatus) SecurityUtils.getSubject().getSession().getAttribute(ZipFileUploadStatus.SESSION_KEY);
//                FileUploadStatus status = new FileUploadStatus(totalBytes);
//                Session session = SecurityUtils.getSubject().getSession();
//                session.setAttribute(FileUploadStatus.SESSION_KEY, status);
//                this.uploadStatus = status;
                break;
            case REQUEST_BYTE_TRANSFER_EVENT:
                this.bytesWritten += bytes;
                if (uploadStatus != null) {
                    uploadStatus.setBytesUploaded(bytesWritten);
                }
                if (this.totalBytes != -1) {
                    int percent = (int)(this.bytesWritten * 100.0 / this.totalBytes);
                    System.out.println(bytes + " bytes have been written at this time, upload progress: " + percent + "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
                } else {
                    System.out.println(bytes + " bytes have been written at this time, upload ratio: unknown" + "(" + this.bytesWritten + "/...)");
                }
                break;
            case TRANSFER_COMPLETED_EVENT:
                this.succeed = true;
                // TODO 可以做成任务模式，构造函数中传递 Runnable 的任务，在这里执行。
                // 上传完成，从session中删除uploadStatus
                SecurityUtils.getSubject().getSession().removeAttribute(FileUploadStatus.SESSION_KEY);
                // 修改字段, 或者将更新OSS key放在这。
                if (designId != 0 && ossKey != null) {
                    designDao.updateZipFileOSSKey(ossKey, designId);
                }
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
    public boolean isSucceed() {
        return succeed;
    }
}