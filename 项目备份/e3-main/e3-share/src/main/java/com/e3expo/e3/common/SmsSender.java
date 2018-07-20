package com.e3expo.e3.common;

/**
 * SMSSender interface
 * 发送短信
 * Created by XINGXu.
 */
public interface SmsSender {
    /**
     * 发送短信
     *
     * @param receivers 收信人，以逗号间隔，每个收件人为13为号码
     * @param message 发件内容
     * @return 返回发送结果
     */
    String send(String receivers, String message) throws Exception;
}
