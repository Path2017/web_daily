package com.e3expo.e3.middleware.service;

import com.e3expo.e3.common.Constants;
import com.e3expo.e3.enumration.DesignerPriceConfigEnum;
import com.e3expo.e3.enumration.EnumOrderStatus;
import com.e3expo.e3.enumration.EnumValidStatus;
import com.e3expo.e3.enumration.OrderNodeEnum;
import com.e3expo.e3.middleware.dao.DesignerBidDao;
import com.e3expo.e3.middleware.dao.DesignerOrderDao;
import com.e3expo.e3.middleware.dao.OrderDesignerLogDao;
import com.e3expo.e3.middleware.dao.OrderDesignerPriceDao;
import com.e3expo.e3.middleware.dao.RfpDao;
import com.e3expo.e3.middleware.dao.UploadFileDao;
import com.e3expo.e3.model.DesignerOrder;
import com.e3expo.e3.model.Order;
import com.e3expo.e3.model.OrderDesignerLog;
import com.e3expo.e3.model.OrderDesignerPrice;
import com.e3expo.e3.model.Rfp;
import com.e3expo.e3.model.UploadFile;
import com.e3expo.e3.model.form.DesignerPriceConfigForm;
import com.e3expo.e3.model.param.PageParam;
import com.e3expo.e3.model.view.WebAppOrderDesignerLogView;
import com.e3expo.e3.model.view.WebAppOrderDesignerView;
import com.e3expo.e3.model.view.WebAppOrderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3expo.e3.middleware.dao.OrderDao;
import com.e3expo.e3.model.OrderModel;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单业务
 * @author luning
 *
 */
@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private RfpDao rfpDao;

	@Autowired
	private DesignerOrderDao designerOrderDao;

	@Autowired
	private OrderDesignerLogDao orderDesignerLogDao;

	@Autowired
	private OrderDesignerPriceDao orderDesignerPriceDao;

	@Autowired
	private DesignerBidDao designerBidDao;

	@Autowired
	private UploadFileDao uploadFileDao;
	
	/**
	 * 根据订单ID 查询订单信息
	 * @param orderId
	 * @return
	 */
	public OrderModel queryOrderInfo(int orderId) {
		OrderModel order = orderDao.selectOrderStatusById(orderId);
		return order;
	}

	/**
	 * 更新订单节点
	 * @param orderId
	 * @param node
	 */
	public void updateOrderNodeById(Integer orderId, OrderNodeEnum node) {
		orderDao.updateOrderNodeById(orderId, node);
	}

	/**
	 * 根据ID更新order的状态
	 * @param orderId
	 * @param status
	 */
	public void updateOrderStatusById(Integer orderId, EnumOrderStatus status) {
		orderDao.updateOrderStatusById(orderId, status);
	}

	/**
	 * 通过ID查询Order
	 * @param orderId
	 * @return
	 */
	public Order getById(Integer orderId) {
		return orderDao.selectById(orderId);
	}

	/**
	 * 插入到te_designer_order表中
	 * @param designerId
	 * @param orderId
	 */
	public void insertDesignerOrder(Integer designerId, Integer orderId) {
		designerOrderDao.insert(orderId, designerId);
	}

	/**
	 * 插入OrderDesignerLog, 并且回写主键
	 * @param orderDesignerLog
	 */
	public void insertOrderDesignerLog(OrderDesignerLog orderDesignerLog) {
		orderDesignerLog.setCreateTime(System.currentTimeMillis());
		orderDesignerLog.setIsValid(EnumValidStatus.VALID.getValue());
//		orderDesignerLog.setStatus();
		orderDesignerLogDao.insert(orderDesignerLog);

	}

	public void updateDesignerOrderNodeById(Integer orderId, OrderNodeEnum node) {
		designerOrderDao.updateNodeIdByOrderId(orderId, node);
	}

	/**
	 * 插入到order_designer_price的表中
	 *
	 * @param form
	 */
	public void insertOrderDesignerPrice(DesignerPriceConfigForm form) {
		List<OrderDesignerPrice> list = new ArrayList<>();
		list.add(new OrderDesignerPrice(DesignerPriceConfigEnum.DESIGN_PRICE.getNodeId(),
				form.getDesignerId(), form.getDesignPrice(), form.getOrderId(),
				DesignerPriceConfigEnum.DESIGN_PRICE.getType(), 0));
		list.add(new OrderDesignerPrice(DesignerPriceConfigEnum.MODIFY_DESIGN_PRICE.getNodeId(),
				form.getDesignerId(), form.getModifyDesignPrice(), form.getOrderId(),
				DesignerPriceConfigEnum.MODIFY_DESIGN_PRICE.getType(), Constants.FREE_MODIFY_DESIGN_TIMES * -1));
		list.add(new OrderDesignerPrice(DesignerPriceConfigEnum.WORKING_DRAWING_PRICE.getNodeId(),
				form.getDesignerId(), form.getWorkingDrawingPrice(), form.getOrderId(),
				DesignerPriceConfigEnum.WORKING_DRAWING_PRICE.getType(), 0));
		orderDesignerPriceDao.batchInsert(list);
	}


	public void increaseOrderDesignerPriceNum(OrderDesignerPrice price) {
		orderDesignerPriceDao.increaseNumById(price);
	}

	/**
	 *
	 * @param type
	 * @param orderId
	 */
	public OrderDesignerPrice selectByTypeAndOrderId(DesignerPriceConfigEnum type, Integer orderId) {
		assert orderId != null;
		return orderDesignerPriceDao.selectByPriceTypeAndOrderId(type, orderId);

	}

	/**
	 * 根据付款类型和orderId查询该类型的报价信息
	 * @param orderId
	 * @param type
	 * @return
	 */
	public OrderDesignerPrice selectOrderDesignerPriceByTypeAndOrderId(Integer orderId, DesignerPriceConfigEnum type) {
		return orderDesignerPriceDao.selectByPriceTypeAndOrderId(type, orderId);
	}

	/**
	 * 查询设计稿更新次数
	 * @param orderId
	 */
	public Integer selectUpdateDesignCountByOrderId(Integer orderId) {
		Integer count = orderDesignerPriceDao.selectUpdateDesignCountByOrderId(orderId);
		if (count == null) {
			return 0;
		}
		return count;
	}

	/**
	 * 根据OrderId查询DesignerOrder
	 * @param orderId
	 * @return
	 */
	public DesignerOrder selectDesignerOrderByOrderId(Integer orderId) {
		return designerOrderDao.selectByOrderId(orderId);
	}

	/**
	 * 分页查询正在进行中的订单
	 *
	 * @param page
	 * @param userId
	 * @return
	 */
	public PageParam<WebAppOrderView> pageQueryOnGoingOrder(PageParam<WebAppOrderView> page, Integer userId) {
		HashSet<EnumOrderStatus> statusSet = new HashSet<>();
		statusSet.add(EnumOrderStatus.ORDER_NORMAL);
		// 查询总条数
		page.setTotal(orderDao.selectOrderTotalCountByStatusAndUserId(userId, statusSet));
		// 查询数据
		List<WebAppOrderView> list = orderDao.pageQueryOrderByStatusAndUserId(page, userId, statusSet);
		page.setData(list);
		return page;
	}

	public PageParam<WebAppOrderView> pageQueryAllOrder(PageParam<WebAppOrderView> page, Integer userId) {
		// 查询总条数
		page.setTotal(orderDao.selectOrderTotalCountByStatusAndUserId(userId, EnumSet.allOf(EnumOrderStatus.class)));
		List<WebAppOrderView> list = orderDao.pageQueryOrderByStatusAndUserId(page, userId, EnumSet.allOf(EnumOrderStatus.class));
		page.setData(list);
		return page;
	}

	public PageParam<WebAppOrderView> pageQueryCompletedOrders(PageParam<WebAppOrderView> page, Integer userId) {
		HashSet<EnumOrderStatus> statusSet = new HashSet<>();
		statusSet.add(EnumOrderStatus.ORDER_COMPLETE);
		// 查询总条数
		page.setTotal(orderDao.selectOrderTotalCountByStatusAndUserId(userId, statusSet));
		List<WebAppOrderView> list = orderDao.pageQueryOrderByStatusAndUserId(page, userId, statusSet);
		page.setData(list);
		return page;
	}

	public PageParam<Rfp> pageQuerySavedRfp(PageParam<Rfp> page, Integer userId) {
		// total
		page.setTotal(rfpDao.selectTotalSavedRfpCount(userId));
		// data
		List<Rfp> list = rfpDao.pageQuerySavedRfp(page, userId);
		page.setData(list);
		return page;
	}

	public Rfp selectRfpById(Integer rfpId) {
		return rfpDao.getById(rfpId);
	}

	public List<WebAppOrderDesignerLogView> selectOrderDesignerLogByOrderId(Integer orderId) {
		return orderDesignerLogDao.selectDesignUploadByOrderId(orderId);
	}

	/**
	 * 根据orderId查询竞标列表
	 *
	 * @param orderId
	 * @return
	 */
	public List<WebAppOrderDesignerView> selectBiddingListByOrderId(Integer orderId) {
		return designerBidDao.selectBidDetailByOrderId(orderId);
	}

	/**
	 * 查询订单对应的设计师
	 *
	 * @param orderId
	 * @return
	 */
	public WebAppOrderDesignerView selectOrderDesignerWebViewByOrderId(Integer orderId) {

		return designerOrderDao.selectWebViewByOrderId(orderId);
	}

	public WebAppOrderView selectRfpWebViewByRfpId(Integer rfpId) {
		return orderDao.selectRfpWebViewByRfpId(rfpId);
	}

	public List<Integer> selectUploadIdByOrderId(Integer id) {
		return orderDesignerLogDao.selectIdByOrderId(id);
//		return null;
	}

	/**
	 * 查询施工图阶段，上传文件的uploadId
	 *
	 * @param orderId
	 * @return
	 */
	public List<Integer> selectWorkingDrawingUploadIdByOrderId(Integer orderId) {
		List<OrderDesignerLog> logs = orderDesignerLogDao.selectByOrderId(orderId);
		List<Integer> after = new LinkedList<>();
		logs.stream().filter(log -> log.getNodeId() != null && log.getNodeId().startsWith("02")).forEach(log -> after.add(log.getId()));
		return after;
	}

	public DesignerBidDao getDesignerBidDao() {
		return designerBidDao;
	}

	/**
	 * 删除指定upload的文件
	 * @param uploadIds
	 */
	public void deleteUploadFilesByUploadId(List<Integer> uploadIds) {
		if (uploadIds != null && uploadIds.size() > 0) {
			uploadFileDao.deleteUploadFilesByUploadId(uploadIds);
		}
	}

	public void insertUploadFile(UploadFile uploadFile) {
		uploadFile.setCreateTime(System.currentTimeMillis());
		uploadFile.setIsValid(EnumValidStatus.VALID.getValue());
		uploadFileDao.insert(uploadFile);
	}

	/**
	 * 分页查询抢单中心列表
	 *
	 * @param page       分页参数
	 * @param designerId 设计师ID
	 * @return
	 */
	public PageParam<WebAppOrderView> pageQueryGrabOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
		assert designerId != null;
		assert page != null;
		List<Integer> badeOrderIds = designerBidDao.selectBadeOrderIdByDesignerId(designerId);
		page.setTotal(orderDao.selectOnBiddingOrderCountByDesignerId(badeOrderIds));
		page.setData(orderDao.pageQueryOnBiddingOrdersByDesignerId(page, badeOrderIds));
		return page;
	}

	public PageParam<WebAppOrderView> pageQueryDesignerAllOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
		assert designerId != null;
		assert page != null;
		page.setTotal(orderDao.selectDesignerAllOrderListCount(designerId));
		page.setData(orderDao.pageQueryDesignerAllOrderList(page, designerId));
		return page;
	}

	/**
	 * 分页查询设计师已完成的订单
	 *
	 * @param page
	 * @param designerId
	 * @return
	 */
	public PageParam<WebAppOrderView> pageQueryDesignerCompletedOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
		assert designerId != null;
		assert page != null;
		page.setTotal(orderDao.selectDesignerCompletedOrderCount(designerId));
		page.setData(orderDao.pageQueryDesignerCompletedOrderList(page, designerId));
		return page;
	}

	/**
	 * 根据
	 * @param rfpId
	 * @return
	 */
	public Order selectOrderByRfpId(Integer rfpId) {
		return orderDao.getOrderByRfpId(rfpId);
	}

	public PageParam<WebAppOrderView> pageQueryDesignerOngoingOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
		assert designerId != null;
		assert page != null;
		page.setTotal(orderDao.selectDesignerOngoingOrderCount(designerId));
		page.setData(orderDao.pageQueryDesignerOngoingOrderList(page, designerId));
		return page;
	}
}
