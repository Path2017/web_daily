package com.e3expo.mms.controller.message;

import java.io.Serializable;

public class MmsMessage implements Serializable  {

	
	public MmsMessage() {
		this.code = 0;
		this.msg = null;
	}

	public MmsMessage(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 *  私有数据和私有方法
	 */
	private int code;
	private String msg;
	
	private static final long serialVersionUID = -6266897805445029839L;
	
	

}
