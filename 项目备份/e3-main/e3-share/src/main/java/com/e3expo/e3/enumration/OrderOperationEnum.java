package com.e3expo.e3.enumration;

/**
 * 设计师操作的枚举
 */
public enum OrderOperationEnum {
    CREATE(1),
    BID(2),
    END_BIDDING(3),
    WAIT_PAY(4),
    PAID(5),
    UPLOAD_DESIGN(6),
    APPLY_MODIFY(7),
    CONFIRM_DESIGN(8),
    UPLOAD_WORKING_DRAWING(9),
    CONFIRM_WORKING_DRAWING(10),
    CANCEL(11),
    TERMINATE(12),
    COMPLETE(13);

    OrderOperationEnum(int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return id;
    }
}
