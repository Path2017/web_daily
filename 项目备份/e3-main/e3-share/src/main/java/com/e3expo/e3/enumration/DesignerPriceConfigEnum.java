package com.e3expo.e3.enumration;

import java.util.EnumSet;

public enum DesignerPriceConfigEnum {
    DESIGN_PRICE("01",1),
    MODIFY_DESIGN_PRICE("01",2),
    WORKING_DRAWING_PRICE("02",1);
    private final String nodeId;
    private final Integer type;

    DesignerPriceConfigEnum(String nodeId, Integer type) {
        this.nodeId = nodeId;
        this.type = type;
    }

    public String getNodeId() {
        return nodeId;
    }

    public Integer getType() {
        return type;
    }

    public static DesignerPriceConfigEnum getEnum(String nodeId, Integer type) {
        assert nodeId != null;
        assert type != null;
        for (DesignerPriceConfigEnum designPriceConfigEnum : EnumSet.allOf(DesignerPriceConfigEnum.class)) {
            if (designPriceConfigEnum.getNodeId().equals(nodeId) && designPriceConfigEnum.getType().equals(type)) {
                return designPriceConfigEnum;
            }
        }
        return null;
    }

}
