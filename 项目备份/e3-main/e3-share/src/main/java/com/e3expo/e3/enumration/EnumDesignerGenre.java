package com.e3expo.e3.enumration;

public enum EnumDesignerGenre {
	
	DESIGNER_OUTSIDE(1, "外部设计师"),
	DESIGNER_SELF(2, "自营设计师");
	private int value;
	private String name;

	private EnumDesignerGenre(int value, String name) {
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

	public static EnumDesignerGenre getType(String name) {
		for (EnumDesignerGenre type : EnumDesignerGenre.values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}
		return null;
	}
}
