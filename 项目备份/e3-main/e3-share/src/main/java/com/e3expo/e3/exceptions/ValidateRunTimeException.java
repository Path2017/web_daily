package com.e3expo.e3.exceptions;

public class ValidateRunTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private BaseErrorCode code;

	public ValidateRunTimeException(BaseErrorCode code) {
		super();
		this.code = code;
	}

	public ValidateRunTimeException() {
		super();
	}

	public ValidateRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ValidateRunTimeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ValidateRunTimeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ValidateRunTimeException(Throwable cause) {
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
