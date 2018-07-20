package com.e3expo.e3.model.view;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.e3expo.e3.model.Order;

@Alias("orderView")
public class OrderView implements Serializable {
	/**
	 * 订单Id
	 */
	private Integer id;
	private String exhibitionCity;
	private String rfpNo;
	private String companyName;
	private String linkManName;
	private String linkManPhone;
	/**
	 * 订单的key,对应EnumOrderAdminStatus的key
	 */
	private Integer statusKey;
	/**
	 * 订单的remark,对应EnumOrderAdminStatus的remark
	 */
	private String statusRemark;
	private Long updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExhibitionCity() {
		return exhibitionCity;
	}

	public void setExhibitionCity(String exhibitionCity) {
		this.exhibitionCity = exhibitionCity;
	}

	public String getRfpNo() {
		return rfpNo;
	}

	public void setRfpNo(String rfpNo) {
		this.rfpNo = rfpNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public String getLinkManPhone() {
		return linkManPhone;
	}

	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}

	public Integer getStatusKey() {
		return statusKey;
	}

	public void setStatusKey(Integer statusKey) {
		this.statusKey = statusKey;
	}

	public String getStatusRemark() {
		return statusRemark;
	}

	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

}
