package com.e3expo.e3.model.form;

import java.io.Serializable;
import java.sql.Date;

public class OrderAdminParam extends AbstractPage implements Serializable {
	/**
	 * 城市
	 */
	private String exhibitionCity;
	/**
	 * 订单号
	 */
	private String rfpNo;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 联系人姓名
	 */
	private String linkManName;
	/**
	 * 联系人电话
	 */
	private String linkManPhone;
	/**
	 * 订单状态值，对应EnumOrderAdminStatus的key
	 */
	private Integer statusKey;
	/**
	 * 对应状态下，订单节点和状态的sql配置
	 */
	private String statusSQL;
	
	/**
	 * 开始时间
	 */
	private Date startDateTime;
	/**
	 * 结束时间
	 */
	private Date endDateTime;
	/**
	 * 开始时间
	 */
	private Long startTime;
	/**
	 * 结束时间
	 */
	private Long endTime;

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

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getStatusSQL() {
		return statusSQL;
	}

	public void setStatusSQL(String statusSQL) {
		this.statusSQL = statusSQL;
	}


}
