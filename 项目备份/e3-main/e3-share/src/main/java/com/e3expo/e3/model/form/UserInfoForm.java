package com.e3expo.e3.model.form;

import java.sql.Date;

import org.springframework.util.StringUtils;

import com.e3expo.e3.enumration.OrderEnum;
import com.e3expo.e3.model.User;
import com.e3expo.e3.util.DateUtils;

public class UserInfoForm extends AbstractPagingSortParam {
	private String mobile;
	private Integer userType;
	private Integer provinceId;
	private Integer status;

	private Integer cityId;
	private String name;
	private Date start;
	private Date end;
	private Long startTime;
	private Long endTime;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Long getStartTime() {
		return DateUtils.getTimeStamp(start);
	}

	public Long getEndTime() {
		return DateUtils.getTimeStamp(end);
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getSortby() {
		return StringUtils.isEmpty(super.getSortby()) ? "create_time" : super.getSortby();
	}

	public OrderEnum getOrder() {
        return super.getOrder() == null ? OrderEnum.DESC : super.getOrder();
    }

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
