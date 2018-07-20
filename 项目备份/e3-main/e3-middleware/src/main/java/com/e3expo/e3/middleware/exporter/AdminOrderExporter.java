package com.e3expo.e3.middleware.exporter;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e3expo.e3.enumration.EnumOrderAdminStatus;
import com.e3expo.e3.middleware.mapper.CityMapper;
import com.e3expo.e3.middleware.mapper.DesignerBidMapper;
import com.e3expo.e3.middleware.mapper.DesignerMapper;
import com.e3expo.e3.middleware.mapper.OrderMapper;
import com.e3expo.e3.middleware.mapper.OrderNodeMapper;
import com.e3expo.e3.middleware.mapper.ProvinceMapper;
import com.e3expo.e3.middleware.mapper.RfpMapper;
import com.e3expo.e3.middleware.mapper.UserAuditLogMapper;
import com.e3expo.e3.middleware.mapper.UserAuditMapper;
import com.e3expo.e3.middleware.mapper.UserAuditRemarkLogMapper;
import com.e3expo.e3.middleware.mapper.UserInfoFileMapper;
import com.e3expo.e3.middleware.mapper.UserMapper;
import com.e3expo.e3.middleware.mapper.UserRemarkLogMapper;
import com.e3expo.e3.model.DesignerBid;
import com.e3expo.e3.model.Order;
import com.e3expo.e3.model.OrderNode;
import com.e3expo.e3.model.form.OrderAdminParam;
import com.e3expo.e3.model.view.OrderDetailView;
import com.e3expo.e3.model.view.OrderView;
import com.e3expo.e3.model.view.PagedData;
import com.e3expo.e3.model.view.RfpView;
import com.e3expo.e3.service.interfaces.IAdminOrder;
/**
 * 后台订单管理
 * @author lizy
 *
 */
@Component
public class AdminOrderExporter implements IAdminOrder {	
	private SqlSessionTemplate sqlSessionTemplate;
	private OrderMapper orderMapper;
	private RfpMapper rfpMapper;
	private OrderNodeMapper orderNodeMapper;
	private DesignerBidMapper designerBidMapper;
	@Autowired
	private void setSqlSessionTemplate(SqlSessionTemplate template) {
		this.sqlSessionTemplate = template;
		this.orderMapper = this.sqlSessionTemplate.getMapper(OrderMapper.class);
		this.rfpMapper=this.sqlSessionTemplate.getMapper(RfpMapper.class);
		this.orderNodeMapper=this.sqlSessionTemplate.getMapper(OrderNodeMapper.class);
		this.designerBidMapper=this.sqlSessionTemplate.getMapper(DesignerBidMapper.class);
	}
	@Override
	public OrderAdminParam getPagedOrderByAdmin(OrderAdminParam param) {
		PagedData<List<OrderView>> pagedData=new PagedData<>();
		List<OrderView> orderViews=this.orderMapper.getPagedOrderByAdmin(param);
		for (OrderView orderView : orderViews) {
			orderView.setStatusRemark(EnumOrderAdminStatus.getRemark(orderView.getStatusKey()));
		}
		Integer totalNum=this.orderMapper.getPagedTotalNumByAdmin(param);
		param.setData(orderViews);
		param.setTotal(totalNum);	
		return param;
	}
	@Override
	public OrderDetailView getOrderDetailView(Integer orderId) {
		
		OrderDetailView orderDetailView=new OrderDetailView();
		orderDetailView=this.orderMapper.getOrderDetailView(orderId);
		//获取rfp
		RfpView rfpView=this.rfpMapper.getRfpAndDetail(orderId);
		//获取订单节点的字典信息
		List<OrderNode> orderNodes=this.orderNodeMapper.getList(orderDetailView.getNodeId());
		//获取报价信息
		List<DesignerBid> designerBids=this.designerBidMapper.getDesignerBid(orderId);
		orderDetailView.setRfpView(rfpView);
		orderDetailView.setOrderNodes(orderNodes);
		orderDetailView.setDesignerBids(designerBids);
		return orderDetailView;
	}
	@Override
	public void updateOrder(Order record) {
		// TODO Auto-generated method stub
		this.orderMapper.updateByPrimaryKeySelective(record);
		
	}

}
