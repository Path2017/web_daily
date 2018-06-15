package com.e3expo.mms.bean.service.oss.schedule.task;


import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.e3expo.mms.bean.service.oss.OSSManager;

import java.io.InputStream;

/**
 * 上传文件的task
 */
public class PutObjectToOSSTask implements Runnable {

    private String bucket;
    private String ossKey;
    private InputStream in;

    private PutObjectRequest putObjectRequest;

    private int designId;
    private long totalLength;

    public PutObjectToOSSTask() {
    }

    public PutObjectToOSSTask(PutObjectRequest putObjectRequest) {
        this.putObjectRequest = putObjectRequest;
    }

    public PutObjectToOSSTask(String bucket, String ossKey, InputStream in) {
        this.bucket = bucket;
        this.ossKey = ossKey;
        this.in = in;
    }

    public PutObjectToOSSTask(String bucket, String ossKey, InputStream in, int designId, long totalLength) {
        this.bucket = bucket;
        this.ossKey = ossKey;
        this.in = in;
        this.designId = designId;
        this.totalLength = totalLength;
    }

    public PutObjectToOSSTask setBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public PutObjectToOSSTask setOssKey(String ossKey) {
        this.ossKey = ossKey;
        return this;
    }

    public PutObjectToOSSTask setInputStream(InputStream in) {
        this.in = in;
        return this;
    }


    @Override
    public void run() {
        // 有request就直接执行请求
        if (putObjectRequest != null) {
            PutObjectResult result =
                    OSSManager.getInstance().putObjectToOSS(putObjectRequest);
            return;
        }
        // 如果没有request，先进行参数验证，然后执行
        if (bucket == null || ossKey == null || in == null) {
            throw new NullPointerException("bucket, ossKey or inputStream is null");
        }
//        System.out.println("bucket name -> " + bucket);
//        System.out.println("ossKey -> " + ossKey);
        if (designId != 0 && totalLength != 0) {
            PutObjectResult result =
                    OSSManager.getInstance().putObjectToOSS(bucket, ossKey, in, designId, totalLength);
        }
        PutObjectResult result =
                OSSManager.getInstance().putObjectToOSS(bucket, ossKey, in);
    }
}
