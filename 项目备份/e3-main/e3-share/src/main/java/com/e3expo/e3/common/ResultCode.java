package com.e3expo.e3.common;

public enum ResultCode {
	
	SUCCESS(200,"success"),
	/**
	 * 系统级错误，级别最高
	 */
	SYSTEM_ERROR(10000,"System.server.error");
	
	
	//*********************
	private int value;
	
	private String desc;

	private static java.util.HashMap<Integer, ResultCode> mappings;

	private synchronized static java.util.HashMap<Integer, ResultCode> getMappings() {
		if (mappings == null) {
			mappings = new java.util.HashMap<Integer, ResultCode>();
			for (ResultCode resultCode : ResultCode.values()) {
				mappings.put(resultCode.value,resultCode);
			}
		}
		return mappings;
	}
	
	public static ResultCode forValue(int value) {
        return getMappings().get(value);
    }
	
	private ResultCode(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
	public boolean isEqual(ResultCode tip){
		return this.value==tip.value;
	}
}
