package com.e3expo.e3.model;

import org.apache.ibatis.type.Alias;

@Alias("orderModel")
public class OrderModel extends Rfp {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3687688757956119066L;

	private Integer status;
	
	private String nodeId;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	
}
