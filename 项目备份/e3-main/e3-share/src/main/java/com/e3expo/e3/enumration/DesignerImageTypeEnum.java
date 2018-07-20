package com.e3expo.e3.enumration;

public enum DesignerImageTypeEnum {
    DESIGNER_REPRESENTATIVE_WORK(1, "designer_representative_work", "representativeWork"),
    DESIGNER_EDUCATION(2, "designer_education", "education"),
    DESIGNER_TITLE(3, "designer_title", "title"),
    DESIGNER_AWARD(4, "designer_award", "award"),
    DESIGNER_ID_CARD_FRONT(5, "designer_id_card_front", "idCardFront"),
    DESIGNER_ID_CARD_BACK(6, "designer_id_card_back", "idCardBack");

    /**
     * 对应数据库中的ID
     */
    private final int id;
    /**
     * 数据库中的名称字段
     */
    private final String name;
    /**
     * 在webapp层中对应的参数名
     */
    private final String webParam;

    DesignerImageTypeEnum(int id, String name, String webParam) {
        this.id = id;
        this.name = name;
        this.webParam = webParam;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWebParam() {
        return webParam;
    }

    public DesignerImageTypeEnum getEnumByWebParam(String webParam) {
        if (webParam == null) {
            return null;
        }
        for (DesignerImageTypeEnum type : DesignerImageTypeEnum.values()) {
            if (webParam.equals(type.getWebParam())) {
                return type;
            }
        }
        return null;
    }
}
