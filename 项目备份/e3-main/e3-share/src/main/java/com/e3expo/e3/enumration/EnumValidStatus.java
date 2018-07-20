package com.e3expo.e3.enumration;
/**
 * is_valid状态值，1有效，-1无效
 * @author lizy
 *
 */
public enum EnumValidStatus {
	VALID(1, "有效"),
	INVALID(-1, "无效");
	private int value;
	private String name;

	private EnumValidStatus(int value, String name) {
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

	public static EnumValidStatus getType(String name) {
		for (EnumValidStatus type : EnumValidStatus.values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}
		return null;
	}

	public static EnumValidStatus getType(int value) {
		for (EnumValidStatus type : EnumValidStatus.values()) {
			if (type.value == value) {
				return type;
			}
		}
		return null;
	}
}
