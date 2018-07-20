package com.e3expo.e3.enumration;

public enum DesignerTypeEnum {

    JUNIOR(1,"junior_designer",0),
    MEDIUM(2,"medium_designer",0),
    SENIOR(3,"senior_designer",0),
    STUDIO(4,"designer_studio",0),
    SELF_SUPPORT(5,"self_support_designer", 0);

    private final int type;
    private final String name;
    private final int level;

    DesignerTypeEnum(int type, String name, int level) {
        this.type = type;
        this.name = name;
        this.level = level;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
