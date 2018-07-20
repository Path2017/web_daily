package com.e3expo.e3.webapp.bean.upload.client;

import com.e3expo.e3.webapp.bean.upload.client.event.ProgressEventType;
import com.e3expo.e3.webapp.bean.upload.client.event.ProgressListener;
import com.e3expo.e3.webapp.bean.upload.client.event.ProgressPublisher;
import com.e3expo.e3.webapp.bean.upload.client.model.UploadRequest;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UploadClient {

    public static int BUFFER_SIZE = 4096 * 2;

    /**
     * 上传文件重载的方法
     * @param in
     * @param uploadTo
     * @throws IOException
     */
    public static void uploadFile(InputStream in, String uploadTo) throws IOException {
        uploadFile(new UploadRequest(in, new FileOutputStream(new File(uploadTo))));
    }

    /**
     * 对外使用的上传文件的方法
     * @param request
     * @throws IOException
     */
    public static void uploadFile(UploadRequest request) throws IOException {
        InputStream in = request.getIn();
        OutputStream out = request.getOut();

        Assert.notNull(in, "No InputStream specified");
        Assert.notNull(out, "No OutputStream specified");

        publishEventIfWithListener(request.getListener(), ProgressEventType.TRANSFER_STARTED_EVENT);
        try {
            doCopy(in, out, request.getListener());
        }
        finally {
            try {
                in.close();
            }
            catch (IOException ex) {
            }
            try {
                out.close();
            }
            catch (IOException ex) {
            }
            publishEventIfWithListener(request.getListener(), ProgressEventType.TRANSFER_COMPLETED_EVENT);
        }
    }

    /**
     * 执行拷贝
     *
     * @param in
     * @param out
     * @param listener
     * @throws IOException
     */
    private static void doCopy(InputStream in, OutputStream out, ProgressListener listener) throws IOException {
        int byteCount = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
            publishEventIfWithListener(listener, ProgressEventType.REQUEST_BYTE_TRANSFER_EVENT, bytesRead);
        }
        out.flush();
    }
    /**
     * 如果包含监听器的发布事件
     * @param listener 监听器
     * @param type     事件类型
     */
    private static void publishEventIfWithListener(ProgressListener listener, ProgressEventType type) {
        if (listener != null) {
            ProgressPublisher.publishProgress(listener, type);
        }
    }

    /**
     * 如果包含监听器的发布事件
     * @param listener      监听器
     * @param type          事件类型
     * @param bytesUploaded 上传的字节数
     */
    private static void publishEventIfWithListener(ProgressListener listener, ProgressEventType type, int bytesUploaded) {
        if (listener != null) {
            ProgressPublisher.publishProgress(listener, type, bytesUploaded);
        }
    }
}
