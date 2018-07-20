package com.e3expo.e3.admin.controller.rest;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.e3expo.e3.common.ResultCode;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author luning
 *
 */
public class RestfulJsonModelAndView extends ModelAndView {
	
	/**
	 * 返回的数据包
	 */
	private final static String DATA = "data";
	/**
	 * 返回处理结果状态
	 */
	private final static String RET = "code";
	
	/**
	 * 返回的错误码
	 */
	private final static String ERROR_MESSAGE = "err_msg";
	
	/**
	 * 返回异常描述
	 */
	private final static String EXCEPTION = "exception";

	/**
	 * 时间处理格式化
	 */
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 返回状态
	 */
	private int ret;
	
	/**
	 * 返回数据
	 */
	private Object data;
	
	/**
	 * 错误消息
	 */
	private String err_msg;
	
	/**
	 * 异常内容
	 */
	private String exception;
	
	public RestfulJsonModelAndView() {
		super(new MappingJackson2JsonView(), new HashMap<String, Object>());
	}
	
	private RestfulJsonModelAndView(MappingJackson2JsonView view, Object data) {
		super(view, new HashMap<String, Object>());
		this.getModel().put(RET, ResultCode.SUCCESS.getValue());
		if(data != null){
			this.getModel().put(DATA, data);
		}
	}
	
	private RestfulJsonModelAndView(int errorCode, String message, String exception) {
		this(errorCode, null, message, exception);
	}
	
	private RestfulJsonModelAndView(int ret, Object data, String message, String exception){
		super(new MappingJackson2JsonView(), new HashMap<String, Object>());
		this.ret = ret;
		this.data = data;
		this.err_msg = message;
		this.exception = exception;
		this.getModel().put(RET, ret);
		if(data != null){
			this.getModel().put(DATA, data);
		}
		if(ret != ResultCode.SUCCESS.getValue()){
			this.getModel().put(ERROR_MESSAGE, err_msg);
			this.getModel().put(EXCEPTION, exception);
		}
	}
	
	/**
	 * 根据传入数据包构造一个成功返回结果视图
	 * @param data
	 * @return
	 */
	public static RestfulJsonModelAndView buildJsonModelAndView(Object data){
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.getObjectMapper().setDateFormat(DATE_FORMAT);
		 TimeZone timeZone1 = TimeZone.getDefault();
			
		view.getObjectMapper().setTimeZone(timeZone1);
		view.getObjectMapper().setSerializationInclusion(Include.NON_NULL);
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        view.getObjectMapper().registerModule(simpleModule);
		return new RestfulJsonModelAndView(view, data);
	}
	
	/**
	 * 构造一个数据包为空的成功返回视图
	 * @return
	 */
	public static RestfulJsonModelAndView buildJsonModelAndView(){
		return buildJsonModelAndView(null);
	}
	
	/**
	 * 构造一个错误返回结果的视图
	 * @param errorCode
	 * @param message
	 * @param exception
	 * @return
	 */
	public static RestfulJsonModelAndView buildError(int errorCode, String message, String exception){
		return new RestfulJsonModelAndView(errorCode, message, exception);
	}

	/**
	 * @return the ret
	 */
	public int getRet() {
		return ret;
	}

	/**
	 * @param ret the ret to set
	 */
	public void setRet(int ret) {
		this.ret = ret;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the err_msg
	 */
	public String getErr_msg() {
		return err_msg;
	}

	/**
	 * @param err_msg the err_msg to set
	 */
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}

	/**
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(String exception) {
		this.exception = exception;
	}
}
