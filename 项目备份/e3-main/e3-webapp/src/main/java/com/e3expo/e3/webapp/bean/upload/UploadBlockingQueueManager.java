package com.e3expo.e3.webapp.bean.upload;

import com.e3expo.e3.webapp.common.GenerateImagePathUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class UploadBlockingQueueManager {
    private final static BlockingQueue<UploadTask> UPLOAD_FILE_BLOCKING_QUEUE = new LinkedBlockingQueue<>();

    static {
        // 启动消费线程
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new UploadFileConsumer());
    }

    /**
     * 上传文件
     * 将InputStream，保存到指定路径的文件中
     *  @param pathTo 上传到的全路径，含文件名
     * @param in     输入流
     * @param totalBytes
     */
    public static void putUploadTask(String pathTo, InputStream in, long totalBytes) throws InterruptedException {
        assert pathTo != null;
        assert in != null;
        UPLOAD_FILE_BLOCKING_QUEUE.put(new UploadTask(pathTo, in, totalBytes));
    }

    /**
     * 上传文件
     *
     * @param multipartFile 表单上传的文件
     */
    public static String uploadMultipartFileAndReturnPath(MultipartFile multipartFile) throws InterruptedException, IOException {
        assert multipartFile != null;
        String imagePath = GenerateImagePathUtil.getUniqueFilePath(multipartFile.getOriginalFilename());
        // 添加到上传任务队列。
        putUploadTask(GenerateImagePathUtil.getFullPath(imagePath), multipartFile.getInputStream(), multipartFile.getSize());
        return imagePath;
    }

    public static UploadTask takeUploadTask() throws InterruptedException {
        return UPLOAD_FILE_BLOCKING_QUEUE.take();
    }


}
