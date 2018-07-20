package com.e3expo.e3.model;

import org.apache.ibatis.type.Alias;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;
import java.util.List;

@Alias("designerBid")
public class DesignerBid implements Serializable {

	private Integer id;

	private Integer orderId;

	private Integer designerId;

	private Integer isSuccess;

	private Long bidTime;

	private Integer isValid;

	private List<OrderDesignerPrice> orderDesignerPrices;
	
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getDesignerId() {
		return designerId;
	}

	public void setDesignerId(Integer designerId) {
		this.designerId = designerId;
	}

	public Integer getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Long getBidTime() {
		return bidTime;
	}

	public void setBidTime(Long bidTime) {
		this.bidTime = bidTime;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public List<OrderDesignerPrice> getOrderDesignerPrices() {
		return orderDesignerPrices;
	}

	public void setOrderDesignerPrices(List<OrderDesignerPrice> orderDesignerPrices) {
		this.orderDesignerPrices = orderDesignerPrices;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}