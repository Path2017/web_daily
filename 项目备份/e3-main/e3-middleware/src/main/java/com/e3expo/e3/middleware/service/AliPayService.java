package com.e3expo.e3.middleware.service;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.e3expo.e3.common.alipay.AlipayPay;
import com.e3expo.e3.common.alipay.AlipayUtil;
import com.e3expo.e3.util.JsonUtil;

@Service
public class AliPayService {
	
	public String payOrder() {
		 AlipayClient client = AlipayUtil.getAlipayClient();
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		AlipayPay param = new AlipayPay();
		param.setOut_trade_no("11111111111");
		param.setTotal_amount(0.01);
		param.setSubject("测试支付");
//		param.setP
		alipayRequest.setReturnUrl("http://1733g5a837.iok.la/e3-webapp/alipay/topay");
		alipayRequest.setNotifyUrl("http://1733g5a837.iok.la/e3-webapp/alipay/notify");
		alipayRequest.setBizContent(JsonUtil.toJson(param));
		String form = null;
		try {
			form = client.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return form;
	}
}
