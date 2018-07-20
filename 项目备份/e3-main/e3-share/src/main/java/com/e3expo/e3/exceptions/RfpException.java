package com.e3expo.e3.exceptions;

public class RfpException extends ValidateException {

	/**
	 *
	 */
	private static final long serialVersionUID = -3234056978978368691L;

	public enum ErrorCode implements BaseErrorCode {

		// RFP信息不存在
		RFP_NOT_EXISTED(200201,"User.not.existed"),
		// RFP详情已经填写
		RFP_DETAIL_ALREADY_EXISTED(200202, "Rfp.detail.already.existed"),
		// RFP详情未填写
		RFP_DETAIL_REQUIRED(20203, "Rfp.detail.required"),
		// RFP已经发布
		RFP_ALREADY_PUBLISHED(20204, "Rfp.already.published"),
		// RFP的状态非法
		RFP_ILLEGAL_STATUS(20205, "Rfp.illegal.status");

		private int value;

		private String desc;

		ErrorCode(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}
	}

	public RfpException() {
		super();
	}

	public RfpException(BaseErrorCode code) {
		super(code);
	}

}
