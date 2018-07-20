package com.e3expo.e3.enumration;

import java.util.EnumSet;

public enum DesignFileTypeEnum {
	
	DESIGNER_REPRESENTATIVE_WORK(1, "designer_representative_work", "designerRepresentativeWork"),
	DESIGNER_EDUCATION(2, "designer_education", "designerEducation"),
	DESIGNER_TITLE(3, "designer_title", "designerTitle"),
	DESIGNER_AWARD(4, "designer_award", "designerAward"),
	DESIGNER_ID_CARD_FRONT(5, "designer_id_card_front", "designerIdCardFront"),
	DESIGNER_ID_CARD_BACK(6, "designer_id_card_back", "designerIdCardBack"),
    DESIGN_LEFT_VIEW(7, "design_left_view", "designLeftView"),
    DESIGN_RIGHT_VIEW(8, "design_right_view", "designRightView"),
    DESIGN_PLAN_VIEW(9, "design_plan_view", "designPlanView"),
    DESIGN_FRONT_GRID_VIEW(10, "design_front_grid_view", "designFrontGridView"),
    DESIGN_PLAN_GRID_VIEW(11, "design_plan_grid_view", "designPlanGridView"),
    DESIGN_OTHER_VIEW(12, "design_other_view", "designOtherView"),
    DESIGN_3DMAX(13, "design_3dmax", "design3DMax"),
    WORKING_DRAWING(14, "working_drawing", "workingDrawing");

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

    DesignFileTypeEnum(int id, String name, String webParam) {
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

    public DesignFileTypeEnum getEnumByWebParam(String webParam) {
        if (webParam == null) {
            return null;
        }
        for (DesignFileTypeEnum type : DesignFileTypeEnum.values()) {
            if (webParam.equals(type.getWebParam())) {
                return type;
            }
        }
        return null;
    }

    public static DesignFileTypeEnum getById(Integer id) {
        for (DesignFileTypeEnum anEnum : EnumSet.allOf(DesignFileTypeEnum.class)) {
            if (anEnum.getId() == id) {
                return anEnum;
            }
        }
        return null;
    }
}
