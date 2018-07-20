package com.e3expo.e3.enumration;

public enum EnumAuditStatus {
	AUDIT_LOAD(1,"待审核"),
	AUDIT_SUCCESS(2,"审核通过"),
	AUDIT_FAIL(-1,"审核失败"),
	AUDIT_NOTCOMPLETED(-2,"资料不全");
	
	private int value;
	private String name;
	
	
	private EnumAuditStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static EnumAuditStatus getType(String name){
		for(EnumAuditStatus type : EnumAuditStatus.values()){
			if(type.name.equals(name)){
				return type;
			}
		}
		return null;
	}
	
	public static EnumAuditStatus getType(int value){
		for(EnumAuditStatus type : EnumAuditStatus.values()){
			if(type.value==value){
				return type;
			}
		}
		return null;
	}
	public static String getRemark(Integer value) {
		for(EnumAuditStatus type : EnumAuditStatus.values()){
			if(type.value==value){
				return type.getName();
			}
		}
		return "此状态不存在";
	}
	
}
