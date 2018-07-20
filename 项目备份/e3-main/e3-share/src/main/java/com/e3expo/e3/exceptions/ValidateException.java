package com.e3expo.e3.exceptions;

public class ValidateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private BaseErrorCode code;

	public ValidateException(BaseErrorCode code) {
		super(code.getDesc());
		this.code = code;

	}

	public ValidateException() {
		super();
	}

	public ValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ValidateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ValidateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BaseErrorCode getCode() {
		return code;
	}

	public void setCode(BaseErrorCode code) {
		this.code = code;
	}

}
