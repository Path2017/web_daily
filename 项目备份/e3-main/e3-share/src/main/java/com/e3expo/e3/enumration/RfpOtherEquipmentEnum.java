package com.e3expo.e3.enumration;

public enum RfpOtherEquipmentEnum {
    FRIDGE(1, "冰箱"),
    WATER_FOUNTAIN(2, "烤漆");

    RfpOtherEquipmentEnum(int id, String name) {
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
