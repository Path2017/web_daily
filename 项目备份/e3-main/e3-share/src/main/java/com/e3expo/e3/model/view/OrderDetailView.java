package com.e3expo.e3.model.view;

import java.io.Serializable;
/**
 * 订单详情
 * @author lizy
 *
 */
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.e3expo.e3.model.DesignerBid;
import com.e3expo.e3.model.Order;
import com.e3expo.e3.model.OrderDesignerLog;
import com.e3expo.e3.model.OrderNode;
import com.e3expo.e3.model.Rfp;
import com.e3expo.e3.model.RfpDetail;

@Alias("orderDetailView")
public class OrderDetailView extends Order {
	/**
	 * 需求详情
	 */
	private RfpView rfpView;
	/**
	 * 订单的流程详情
	 */
	
	private List<OrderDesignerLog> orderDesignerLogs;
	/**
	 * 订单的报价详情
	 */
	
	private List<DesignerBid> designerBids;
	/**
	 * 订单的流程节点的字典信息
	 */
	
	private List<OrderNode> orderNodes;

	public List<OrderNode> getOrderNodes() {
		return orderNodes;
	}

	public void setOrderNodes(List<OrderNode> orderNodes) {
		this.orderNodes = orderNodes;
	}

	public RfpView getRfpView() {
		return rfpView;
	}

	public void setRfpView(RfpView rfpView) {
		this.rfpView = rfpView;
	}

	public List<OrderDesignerLog> getOrderDesignerLogs() {
		return orderDesignerLogs;
	}

	public void setOrderDesignerLogs(List<OrderDesignerLog> orderDesignerLogs) {
		this.orderDesignerLogs = orderDesignerLogs;
	}

	public List<DesignerBid> getDesignerBids() {
		return designerBids;
	}

	public void setDesignerBids(List<DesignerBid> designerBids) {
		this.designerBids = designerBids;
	}
	

}
