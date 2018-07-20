package com.e3expo.e3.model.param;

import com.e3expo.e3.enumration.OrderEnum;
import com.e3expo.e3.model.form.AbstractPagingSortParam;
import org.springframework.util.StringUtils;

public class OrderParam extends AbstractPagingSortParam {
	
	private Integer designerId;
	
	
	public String getSortby() {
        return StringUtils.isEmpty(super.getSortby()) ? "create_time" : super.getSortby();
    }
	
	public OrderEnum getOrder() {
        return super.getOrder() == null ? OrderEnum.DESC : super.getOrder();
    }

	public Integer getDesignerId() {
		return designerId;
	}

	public void setDesignerId(Integer designerId) {
		this.designerId = designerId;
	}
	
	
}
