package com.e3expo.e3.enumration;

public enum StructureEnum {
    WOODEN(1, "木质"),
    TRUSS(2, "桁架"),
    SECTION_BAR(3, "型材");

    StructureEnum(int id, String name) {
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
