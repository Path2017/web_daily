package com.e3expo.e3.enumration;

public enum EnumOrderAdminStatus {
	QUOTATION(1, "报价中", "  (a1.node_id='0101' and a1.`status`=0) "), 
	DESIGN_PAYMENT_PENDING(2, "设计待付款", "  (a1.node_id='0102' and a1.`status`=0) "), 
	DESIGN_SECTION(3, "设计阶段", "  (a1.node_id='0104' and a1.`status`=0)"), 
	GHRAF_PAPER_PAYMENT_PENDING(4, "图纸待付款", "  (a1.node_id='0201' and a1.`status`=0) "), 
	CONSTRUCT_UPLOAD(5, "施工上传", "  (a1.node_id='0202' and a1.`status`=0) "), 
	COMPLETED(6, "已完成", "  (a1.node_id='99' and a1.`status`=0) "), 
	CANCELED(7, "已取消", "   a1.`status`=1 "), 
	TERMINATED(8, "已终止", "   a1.`status`=2 ");
	private Integer key;
	private String remark;
	private String component;

	EnumOrderAdminStatus(Integer key, String remark, String component) {
		this.key = key;
		this.remark = remark;
		this.component = component;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public static EnumOrderAdminStatus getType(Integer key) {
		for (EnumOrderAdminStatus item : EnumOrderAdminStatus.values()) {
			if (item.key.equals(key)) {
				return item;
			}
		}
		return null;
	}
	public static String getRemark(Integer key) {
		for (EnumOrderAdminStatus item : EnumOrderAdminStatus.values()) {
			if (item.key.equals(key)) {
				return item.getRemark();
			}
		}
		return "暂无此状态，请联系开发人员！";
	}
	public static String getStatusSQL(Integer key) {
		for (EnumOrderAdminStatus item : EnumOrderAdminStatus.values()) {
			if (item.key.equals(key)) {
				return item.getComponent();
			}
		}
		return " and 1=1 ";
	}

}
