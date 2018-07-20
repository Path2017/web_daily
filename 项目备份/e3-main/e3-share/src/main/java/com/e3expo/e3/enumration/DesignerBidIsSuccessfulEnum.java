package com.e3expo.e3.enumration;

public enum  DesignerBidIsSuccessfulEnum {
    SUCCESSFUL(1),
    UNSUCCESSFUL(-1);
    private final int value;

    DesignerBidIsSuccessfulEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
