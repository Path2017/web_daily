package com.e3expo.e3.enumration;

import java.util.EnumSet;

/**
 * 订单节点枚举
 */
public enum OrderNodeEnum {

    BIDDING("报价中", "0101"),
    DESIGN_WAITING_PAY("设计待付款", "0102"),
    DESIGN_STAGE("设计阶段", "0103"),
    DESIGN_STAGE_WAIT_UPLOAD("设计阶段-设计稿待提交","010301"),
    DESIGN_STAGE_WAIT_CONFIRM("设计阶段-设计稿待确认","010302"),
    WORKING_DRAWING_WAIT_PAY("施工图待付款", "0201"),
    WORKING_DRAWING_DESIGN_STAGE("施工图设计阶段", "0202"),
    WORKING_DRAWING_WAIT_UPLOAD("施工图设计阶段-施工图待上传", "020201"),
    WORKING_DRAWING_WAIT_CONFIRM("施工图设计阶段-施工图待确认", "020202"),
    COMPLETED("订单已完成", "99");

    OrderNodeEnum(String name, String nodeId) {
        this.name = name;
        this.nodeId = nodeId;
    }

    private final String name;
    private final String nodeId;

    public String getName() {
        return name;
    }

    public String getNodeId() {
        return nodeId;
    }

    public static OrderNodeEnum getByNodeId(String nodeId) {
        EnumSet<OrderNodeEnum> orderNodeEnums = EnumSet.allOf(OrderNodeEnum.class);
        for (OrderNodeEnum anEnum : orderNodeEnums) {
            if (anEnum.getNodeId().equals(nodeId)) {
                return anEnum;
            }
        }
        return null;
    }
}
