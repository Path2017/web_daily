package com.e3expo.mms.bean.service.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.e3expo.mms.bean.service.oss.listener.PutObjectProgressListener;
import com.e3expo.mms.tool.GetPropertiesToHashMap;

import java.io.InputStream;
import java.util.HashMap;

public class OSSManager{

//    private  String externalEndpoint = "oss-cn-shanghai.aliyuncs.com";
//    private String internalEndpoint = "oss-cn-shanghai.aliyuncs.com";
//    private  String accessKeyId = "LTAILZTyF7hCpXD5";
//    private  String accessKeySecret = "OiFZ5g0rA1FvyYW1ii5nQMFSQ3Wkbl";

//    private final OSSClient externalClient = new OSSClient(externalEndpoint, accessKeyId, accessKeySecret);
    private final OSSClient client;
    private final OSSClient externalClient;

    private OSSManager() {
        HashMap<String, String> map = GetPropertiesToHashMap.getMap("mms-server.properties");
        String endpoint = map.get("oss.endpoint");
        String externalEndpoint = map.get("oss.externalEndpoint");
        String accessKeyId = map.get("oss.accessKeyId");
        String accessKeySecret = map.get("oss.accessKeySecret");
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        externalClient = new OSSClient(externalEndpoint, accessKeyId, accessKeySecret);
    }

    private static OSSManager instance = new OSSManager();

    public static OSSManager getInstance() {
        return instance;
    }

    public OSSClient getClient() {
        return externalClient;
    }


//    public OSSClient getExternalClient() {
//        return externalClient;
//    }
    public OSSClient getInternalClient() {
        return client;
    }


    public PutObjectResult putObjectToOSS(String bucket, String ossKey, InputStream in) {
        assert bucket != null && ossKey != null && in != null;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, ossKey, in);
        return client.putObject(putObjectRequest);

    }

    public PutObjectResult putObjectToOSS(String bucket, String ossKey, InputStream in, int designId, long totalLength) {
        assert bucket != null && ossKey != null && in != null;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, ossKey, in)
                .<PutObjectRequest>withProgressListener(new PutObjectProgressListener(totalLength, designId, ossKey));
        return client.putObject(putObjectRequest);
    }

    public PutObjectResult putObjectToOSS(PutObjectRequest putObjectRequest) {
        return client.putObject(putObjectRequest);
    }

}