package com.e3expo.e3.enumration;

public enum ExhibitionGoalEnum {
    BRAND_PROMOTION(1, "品牌推广"),
    PRODUCTS(2, "产品展示"),
    NEW_RELEASES(3, "新品发布"),
    FIELD_SALES(4, "现场销售");

    ExhibitionGoalEnum(int id, String name) {
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
