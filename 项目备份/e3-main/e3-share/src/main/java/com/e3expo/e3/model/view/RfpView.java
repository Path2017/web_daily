package com.e3expo.e3.model.view;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.e3expo.e3.model.Rfp;
import com.e3expo.e3.model.RfpDetail;
/**
 * rfp详情
 * @author lizy
 *
 */
@Alias("rfpView")
public class RfpView extends Rfp implements Serializable {
	private RfpDetail rfpDetail;
	public RfpDetail getRfpDetail() {
		return rfpDetail;
	}
	public void setRfpDetail(RfpDetail rfpDetail) {
		this.rfpDetail = rfpDetail;
	}
	
}
