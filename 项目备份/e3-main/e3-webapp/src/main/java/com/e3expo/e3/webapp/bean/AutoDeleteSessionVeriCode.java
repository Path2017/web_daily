package com.e3expo.e3.webapp.bean;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

import static com.e3expo.e3.webapp.bean.schedule.ScheduledExecutorManager.DELETE_VERICODE_IN_SESSION__EXECUTOR;


public class AutoDeleteSessionVeriCode {

    /**
     * Session中保存验证码的KEY，通过这个KEY可以获取当前的对象
     */
    public static final String VERI_CODE_IN_SESSION_KEY = "VERI_CODE";

    private final HttpSession session;
    private final String veriCode;
    private final String mobile;

    /**
     * 创建对象时将veriCode和mobile信息放入指定session中，并且在指定持续时间之后从session中删除这个验证码-手机号对
     *
     * @param session  指定session
     * @param veriCode 验证码
     * @param mobile   发送验证码的手机号码
     * @param duration 在session中保存的时间，单位是秒
     */
    public AutoDeleteSessionVeriCode(HttpSession session, String veriCode, String mobile,int duration) {
        this.session = session;
        this.veriCode = veriCode;
        this.mobile = mobile;
        // 将this放入Session中
        session.setAttribute(VERI_CODE_IN_SESSION_KEY, this);
        // 设置定时任务删除，在指定秒数后，删除session中验证码
        DELETE_VERICODE_IN_SESSION__EXECUTOR.schedule(
                () ->  session.removeAttribute(VERI_CODE_IN_SESSION_KEY),
                duration, TimeUnit.SECONDS);
    }

    public String getVeriCode() {
        return veriCode;
    }

    public String getMobile() {
        return mobile;
    }
}
