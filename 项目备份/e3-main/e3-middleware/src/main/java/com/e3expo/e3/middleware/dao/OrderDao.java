package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.enumration.EnumOrderStatus;
import com.e3expo.e3.enumration.OrderNodeEnum;
import com.e3expo.e3.model.Order;
import com.e3expo.e3.model.param.PageParam;
import com.e3expo.e3.model.view.WebAppOrderView;
import com.e3expo.e3.validation.group.OrderGroup;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3expo.e3.middleware.mapper.OrderMapper;
import com.e3expo.e3.model.OrderModel;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class OrderDao {
    @Autowired
    private void setSql(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(OrderMapper.class);
    }

    private OrderMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 根据订单ID查询订单当前状态
     *
     * @param orderId
     * @return
     */
    public OrderModel selectOrderStatusById(int orderId) {
        return mapper.selectOrderStatusById(orderId);
    }

    /**
     * 修改订单状态
     *
     * @param order
     */
    public void updateOrderStatus(OrderModel order) {
        order.setUpdateTime(System.currentTimeMillis());
        mapper.updateOrderStatus(order);
    }

    /**
     * 修改订单状态
     * @param orderId
     * @param status
     */
    public void updateOrderStatusById(Integer orderId, EnumOrderStatus status) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(status.getValue());
        order.setUpdateTime(System.currentTimeMillis());
        mapper.updateByPrimaryKeySelective(order);
    }


    /**
     * 插入一条Order记录，并回写主键
     *
     * @param order
     */
    public int insert(@Validated({OrderGroup.Insert.class}) Order order) {
        mapper.insert(order);
        return order.getId();
    }

    /**
     * 根据id查询Order
     *
     * @param orderId
     */
    public Order selectById(Integer orderId) {
        return mapper.selectByPrimaryKey(orderId);
    }

    /**
     * 修改订单节点
     * @param orderId
     * @param node
     */
    public void updateOrderNodeById(Integer orderId, OrderNodeEnum node) {
        Order order = new Order();
        order.setId(orderId);
        order.setNodeId(node.getNodeId());
        order.setUpdateTime(System.currentTimeMillis());
        mapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 通过rfpId查询Order
     *
     * @param rfpId
     */
    public Order getOrderByRfpId(Integer rfpId) {
        return mapper.selectByRfpId(rfpId);
    }

    /**
     * 根据user和订单状态分页查询
     * @param page
     * @param userId
     * @param status
     */
    public List<WebAppOrderView> pageQueryOrderByStatusAndUserId(PageParam<WebAppOrderView> page, Integer userId, Set<EnumOrderStatus> status) {
        Set<Integer> statusSet = new HashSet<>();
        status.forEach(s -> statusSet.add(s.getValue()));
        return mapper.pageQueryOrderByStatusAndUserId(page, userId, statusSet);
    }

    public int selectOrderTotalCountByStatusAndUserId(Integer userId, Set<EnumOrderStatus> status) {
        Set<Integer> statusSet = new HashSet<>();
        status.forEach(s -> statusSet.add(s.getValue()));
        return mapper.selectOrderTotalCountByStatusAndUserId(userId, statusSet);
    }

    public WebAppOrderView selectRfpWebViewByRfpId(Integer rfpId) {
        return mapper.selectRfpWebViewByRfpId(rfpId);
    }

    /**
     * 根据设计师的ID，查询竞标中（可以抢单的）的订单总数
     * @param badeIds
     * @return
     */
    public int selectOnBiddingOrderCountByDesignerId(List<Integer> badeIds) {
        if (badeIds != null && badeIds.size() > 0) {
            return mapper.selectOnBiddingOrderCountByDesignerId(badeIds);
        } else {
            return mapper.selectOnBiddingOrderCountByDesignerId(null);
        }

    }

    /**
     * 分页查询抢单中心
     * @param page
     * @param badeIds
     * @return
     */
    public List<WebAppOrderView> pageQueryOnBiddingOrdersByDesignerId(PageParam<WebAppOrderView> page, List<Integer> badeIds) {
        if (badeIds != null && badeIds.size() > 0) {
            return mapper.pageQueryOnBiddingOrdersByDesignerId(page, badeIds);
        } else {
            return mapper.pageQueryOnBiddingOrdersByDesignerId(page, null);
        }
    }

    public int selectDesignerAllOrderListCount(Integer designerId) {
        return mapper.selectDesignerAllOrderListCount(designerId);
    }

    public List<WebAppOrderView> pageQueryDesignerAllOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
        return mapper.pageQueryDesignerAllOrderList(page, designerId);
    }

    public int selectDesignerCompletedOrderCount(Integer designerId) {
        return mapper.selectDesignerCompletedOrderCount(designerId);
    }

    public List<WebAppOrderView> pageQueryDesignerCompletedOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
        return mapper.pageQueryDesignerCompletedOrderList(page, designerId);
    }

    public int selectDesignerOngoingOrderCount(Integer designerId) {
        return mapper.selectDesignerOngoingOrderCount(designerId);
    }

    public List<WebAppOrderView> pageQueryDesignerOngoingOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
        return mapper.pageQueryDesignerOngoingOrderList(page, designerId);
    }
}
