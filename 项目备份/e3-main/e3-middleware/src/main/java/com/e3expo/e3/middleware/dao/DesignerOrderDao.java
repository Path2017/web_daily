package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.enumration.EnumValidStatus;
import com.e3expo.e3.enumration.OrderNodeEnum;
import com.e3expo.e3.model.DesignerOrder;
import com.e3expo.e3.model.OrderDesignerPrice;
import com.e3expo.e3.model.view.WebAppOrderDesignerView;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3expo.e3.middleware.mapper.DesignerOrderMapper;

import java.util.List;

@Repository
public class DesignerOrderDao {

	@Autowired
	private void setSql(SqlSessionTemplate template) {
	    this.sqlSessionTemplate = template;
	    this.mapper = this.sqlSessionTemplate.getMapper(DesignerOrderMapper.class);
	}
	
	private DesignerOrderMapper mapper;
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	/**
	 * 查询设计师当前正在进行中的订单的数量
	 * @param designerId
	 * @return
	 */
	public int selectCountOrderingDesigner(int designerId) {
		return mapper.selectCountOrderingDesigner(designerId);
	}

	/**
	 * 插入一条记录
	 *
	 * @param orderId
	 * @param designerId
	 */
	public void insert(Integer orderId, Integer designerId) {
		DesignerOrder designerOrder = new DesignerOrder(orderId, designerId);
		long l = System.currentTimeMillis();
		designerOrder.setCreateTime(l);
		designerOrder.setUpdateTime(l);
		designerOrder.setIsValid(EnumValidStatus.VALID.getValue());
		designerOrder.setNodeId(OrderNodeEnum.DESIGN_STAGE_WAIT_UPLOAD.getNodeId());
		mapper.insertSelective(designerOrder);
	}

	public void updateNodeIdByOrderId(Integer orderId, OrderNodeEnum node) {
		DesignerOrder designerOrder = new DesignerOrder();
		designerOrder.setNodeId(node.getNodeId());
		designerOrder.setOrderId(orderId);
		designerOrder.setUpdateTime(System.currentTimeMillis());
		mapper.updateNodeIdByOrderId(designerOrder);
	}

	/**
	 *
	 * @param orderId
	 * @return
	 */
	public DesignerOrder selectByOrderId(Integer orderId) {
		return mapper.selectByOrderId(orderId);
	}

	public WebAppOrderDesignerView selectWebViewByOrderId(Integer orderId) {

		WebAppOrderDesignerView webView = mapper.selectWebViewByOrderId(orderId);
		List<OrderDesignerPrice> orderDesignerPrice = mapper.selectDesignerBiddingPrice(orderId);
		DesignerBidDao.setOrderPriceMethod(orderDesignerPrice, webView);
		return webView;
	}

//	/**
//	 * 添加可抢单设计师
//	 * @param designerOrderList
//	 */
//	public void insertDesignerLinkOrder(List<DesignerOrder> designerOrderList){
//		mapper.insertDesignerLinkOrder(designerOrderList);
//	}
	
//	/**
//	 * 获取设计师可抢单列表
//	 * @param param
//	 * @return
//	 */
//	public List<OrderModel> selectSingleOrderListByDesignerId(OrderParam param){
//		return mapper.selectSingleOrderListByDesignerId(param);
//	}
}
