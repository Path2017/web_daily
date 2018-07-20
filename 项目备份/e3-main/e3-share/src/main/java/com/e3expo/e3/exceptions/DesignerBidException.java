package com.e3expo.e3.exceptions;

public class DesignerBidException extends ValidateException {


	public enum ErrorCode implements BaseErrorCode {

		// 设计师保健模板校验非法
		BID_ILLEGAL_DESIGNER_PRICE_CONFIG(200201,"Bid.illegal.designer.price.config");


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

	public DesignerBidException() {
		super();
	}

	public DesignerBidException(BaseErrorCode code) {
		super(code);
	}

}
