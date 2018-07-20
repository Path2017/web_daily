package com.e3expo.e3.webapp.bean.upload;

import com.e3expo.e3.webapp.bean.upload.client.UploadClient;
import com.e3expo.e3.webapp.bean.upload.client.event.SaveProgressInRedisListener;
import com.e3expo.e3.webapp.bean.upload.client.model.UploadRequest;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 上传文件消费者
 */
public class UploadFileConsumer implements Runnable {

    @Override
    public void run() {
        while (true) {
            UploadTask uploadTask = null;
            try {
                uploadTask = UploadBlockingQueueManager.takeUploadTask();
            } catch (InterruptedException e) {
                // todo
                e.printStackTrace();
            }
            if (uploadTask != null) {
                try {
                    // copy文件
                    Path filePath = Paths.get(uploadTask.getPathTo());
                    Path folderPath = filePath.getParent();
                    if (!Files.exists(folderPath)) {
                        Files.createDirectories(folderPath);
                    }
                    // 如果文件存在就删除
                    Files.deleteIfExists(filePath);
                    // todo 复制文件
//                    FileCopyUtils.copy(uploadTask.getUploadFileIn(),
//                            new FileOutputStream(new File(uploadTask.getPathTo())));
                    UploadRequest uploadRequest = new UploadRequest(uploadTask.getUploadFileIn(),
                            new FileOutputStream(new File(uploadTask.getPathTo())))
                            // 添加listener
                            .withProgressListener(new SaveProgressInRedisListener(uploadTask.getTotalBytes(), uploadTask.getPathTo()));
                    UploadClient.uploadFile(uploadRequest);
                } catch (IOException e) {
                    // TODO 还未处理
                    e.printStackTrace();
//                    throw new RuntimeException(e);
                }
            }
        }
    }
}
