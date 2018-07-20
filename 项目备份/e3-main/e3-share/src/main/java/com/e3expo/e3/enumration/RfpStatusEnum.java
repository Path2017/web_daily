package com.e3expo.e3.enumration;

public enum RfpStatusEnum {

	BASE_INFO_DRAFT(1, "基本信息未填完, 草稿"),
	DETAIL_DRAFT(2, "设计需求未填完, 草稿"),
	PUBLISHED(3,"已发布"),;
	private int value;
	private String name;

	RfpStatusEnum(int value, String name) {
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

	public static RfpStatusEnum getType(String name) {
		for (RfpStatusEnum type : RfpStatusEnum.values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}
		return null;
	}
}
