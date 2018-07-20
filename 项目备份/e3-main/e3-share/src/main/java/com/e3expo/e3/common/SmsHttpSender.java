package com.e3expo.e3.common;

import com.e3expo.e3.exceptions.FailedHttpStatusWhenSendSmsException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

/**
 * SMSSender 的 253短信服务实现类
 * <p>
 * Created by XINGXu.
 */
public class SmsHttpSender implements SmsSender {

    private SmsHttpSender() {
    }

    private static HttpClient defaultHttpClient = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
    ;

    private final static String URL = "http://smssh1.253.com/msg/send";// 应用地址
    private final static String UN = "N3013530";// 账号
    private final static String PW = "XrwvfubjtPb336";// 密码
    private final static String RD = "1";// 是否需要状态报告，需要1，不需要0
    private final static String EX = null;// 扩展码

    private final static int PARAM_PHONE_INDEX = 4;
    private final static int PARAM_MESSAGE_INDEX = 5;

    private static NameValuePair[] smsParams;

    private static SmsHttpSender instance = new SmsHttpSender();


    static {
        smsParams = new NameValuePair[6];

        smsParams[0] = new NameValuePair("un", UN);
        smsParams[1] = new NameValuePair("pw", PW);
        smsParams[2] = new NameValuePair("rd", RD);
        smsParams[3] = new NameValuePair("ex", EX);
        smsParams[4] = new NameValuePair("phone", null);
        smsParams[5] = new NameValuePair("msg", null);

    }

    /**
     * @param url
     * @param un
     * @param pw
     * @param phone
     * @param msg
     * @param rd
     * @param ex
     * @return
     * @throws IOException
     */
    private String send(String url, String un, String pw, String phone, String msg,
                        String rd, String ex) throws IOException {
        GetMethod method = new GetMethod();
        try {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "send", false));
            smsParams[PARAM_PHONE_INDEX].setValue(phone);
            smsParams[PARAM_MESSAGE_INDEX].setValue(msg);
            method.setQueryString(smsParams);
            int result = defaultHttpClient.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                return URLDecoder.decode(baos.toString(), "UTF-8");
            } else {
                throw new FailedHttpStatusWhenSendSmsException("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        } finally {
            method.releaseConnection();
        }
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static SmsHttpSender getInstance() {
        return instance;
    }

    /**
     *
     * @param receivers 收信人，以逗号间隔，每个收件人为13为号码
     * @param message 发件内容
     * @return 响应的字符串
     * @throws IOException Client连接短信服务器失败异常
     */
    @Override
    public String send(String receivers, String message) throws IOException {
        String returnString = send(URL, UN, PW, receivers, message, RD, EX);
        System.out.println(returnString);
        return returnString;
    }

}
