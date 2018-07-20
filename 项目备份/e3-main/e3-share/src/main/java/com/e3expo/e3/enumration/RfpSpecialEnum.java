package com.e3expo.e3.enumration;

public enum RfpSpecialEnum {
    DOUBLE_DECK(1, "双层结构"),
    SUSPENDED_CEILING(2, "吊顶");

    RfpSpecialEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private final int id;
    private final String name;

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

}
