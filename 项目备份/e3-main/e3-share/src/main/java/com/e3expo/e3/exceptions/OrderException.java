package com.e3expo.e3.exceptions;

public class OrderException extends ValidateException {

	public enum ErrorCode implements BaseErrorCode {

		// order信息不存在
		ORDER_NOT_EXISTED(200301,"Order.not.existed"),
		ORDER_NODE_ID_IS_NULL(20302, "Order.node.id.is.null"),
		ORDER_TOO_MANY_BIDS(20303,"Order.too.many.bids" ),
		ORDER_ILLEGAL_STATUS(20304, "Order.illegal.status"),
		// 抢单已经结束
		ORDER_BIDDING_FINISHED(20305, "Order.bidding.finished"),
		// 订单已抢过
		ORDER_ALREADY_BADE(20306, "Order.already.bade"),
		// 订单中的操作用户不匹配
		ORDER_USER_MISMATCH(20307, "Order.user.mismatch"),
		// 订单没有人投标
		ORDER_NO_BIDS(20308, "Order.no.bids"),
		// 报价不存在
		ORDER_DESIGNER_PRICE_NOT_EXIST(20309, "Order.designer.price.not.exist"),
		// 订单修改次数非法
		ORDER_ILLEGAL_UPDATE_DESIGN_COUNT(20310, "Order.illegal.update.design.count"),
		// 订单修改设计稿未付款
		ORDER_UPDATE_DESIGN_PRICE_NOT_PAID(20311, "Order.update.design.price.not.paid"),
		// 订单的节点不对
		ORDER_WRONG_NODE_ID(20312, "Order.wrong.node.id"),
		// 订单未与设计师关联
		ORDER_DESIGNER_ORDER_NOT_EXIST(20313, "Order.designer.order.not.exist"),
		// 订单已经付款，不要重复付款
		ORDER_ALREADY_PAID(20314, "Order.already.paid");

		private int value;

		private String desc;

		ErrorCode(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}
	}

	public OrderException() {
		super();
	}

	public OrderException(BaseErrorCode code) {
		super(code);
	}

}
