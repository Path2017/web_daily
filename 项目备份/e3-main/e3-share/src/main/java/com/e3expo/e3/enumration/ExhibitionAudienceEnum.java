package com.e3expo.e3.enumration;

public enum ExhibitionAudienceEnum {
    PUBLIC_CONSUMER(1, "大众消费者"),
    NATIVE_BUYER(2, "国内买家"),
    FOREIGN_BUYER(3, "国外买家"),
    PROFESSIONAL(4, "专业观众"),
    HONOURED_GUEST(5, "受邀嘉宾");

    ExhibitionAudienceEnum(int id, String name) {
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
