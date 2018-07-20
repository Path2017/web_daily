package com.e3expo.e3.exceptions;

/**
 * 使用Http请求发送短信时，响应状态不为200时抛出异常
 */
public class FailedHttpStatusWhenSendSmsException extends RuntimeException {

    public FailedHttpStatusWhenSendSmsException(String message) {
        super(message);
    }
}
