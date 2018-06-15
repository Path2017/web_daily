package com.e3expo.mms.controller.message;

import java.io.Serializable;

public class HouseError implements Serializable {

	public HouseError() {
		this.code = 0;
		this.error = null;
	}
	
	public HouseError(int code, String error) {
		this.code = code;
		this.error = error;
	}
	
	
	public int getCode() {
		return code;
	}

	public String getError() {
		return error;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setError(String error) {
		this.error = error;
	}

	private int code;
	private String error;
	
	private static final long serialVersionUID = -188234950043256153L;
	
	

}
