package com.e3expo.e3.enumration;

public enum MaterialEnum {
    COATING(1, "涂料"),
    BAKING_FINISH(2, "烤漆"),
    FIRE_PLATE(3, "防火板"),
    STICKY_NOTES(4, "即时贴");

    MaterialEnum(int id, String name) {
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
