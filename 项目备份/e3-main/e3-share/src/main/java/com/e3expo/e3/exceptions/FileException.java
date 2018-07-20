package com.e3expo.e3.exceptions;

public class FileException extends ValidateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6136585276490437035L;

	public static enum ErrorCode implements BaseErrorCode {
		
		// 文件太大
		FILE_SIZE_WRONG(200401, "file.size.wrong"),
		// 文件格式错误
		FILE_TYPE_WRONG(200402, "file.type.wrong"),
		// 文件数量错误
		FILE_NUMER_WRONG(200403, "file.number.wrong"),
		// 文件配置信息不存在
		FILE_KEY_WRONG(200404, "file.key.wrong"),
		// 文件配置信息不存在
	    FILE_KEY_NOT_EXISTED(200405, "file.key.not.existed"),
	    //文件数据为空
	    FILE_IS_NULL(200406,"file.is.null")
	    ;
		private int value;

		private String desc;

		private ErrorCode(int value, String desc) {
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

	public FileException() {
		super();
	}

	public FileException(BaseErrorCode code) {
		super(code);
	}
}
