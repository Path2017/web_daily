package com.e3expo.e3.enumration;

public enum EnumUserType {
//	USERTYPE_DESIGNERROOM(0, "设计工作室"),
	DESIGNER(1, "设计师"),
	EXHIBITOR(2, "展览公司");
	private int value;
	private String name;

	private EnumUserType(int value, String name) {
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

	public static EnumUserType getType(String name) {
		for (EnumUserType type : EnumUserType.values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}
		return null;
	}

	public static EnumUserType getType(int value) {
		for (EnumUserType type : EnumUserType.values()) {
			if (type.value == value) {
				return type;
			}
		}
		return null;
	}
}
