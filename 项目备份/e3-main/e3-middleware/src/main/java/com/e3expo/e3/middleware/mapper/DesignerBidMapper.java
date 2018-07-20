package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.DesignerBid;
import com.e3expo.e3.model.OrderDesignerPrice;
import com.e3expo.e3.model.view.WebAppOrderDesignerView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DesignerBidMapper {

	List<DesignerBid> selectByOrderId(Integer orderId);

	void insert(DesignerBid bid);

	void updateIsSuccessByDesignerId(@Param("isSuccess") int value,
									 @Param("designerIds") List<Integer> designerIds);

	List<DesignerBid> getDesignerBid(Integer orderId);

	/**
	 * 查询竞标的详情
	 *
	 * @param orderId
	 * @return
	 */
    List<WebAppOrderDesignerView> selectBidDetailByOrderId(Integer orderId);

	/**
	 * 查询用户对指定订单竞标价格
	 *
	 * @param userId
	 * @param orderId
	 * @return
	 */
	List<OrderDesignerPrice> selectDesignerBiddingPrice(@Param("userId") Integer userId,
														@Param("orderId") Integer orderId);

	/**
	 * 查询已经竞标过了订单ID列表
	 *
	 * @return
	 * @param designerId
	 */
    List<Integer> selectBadeOrderIdByDesignerId(Integer designerId);

}