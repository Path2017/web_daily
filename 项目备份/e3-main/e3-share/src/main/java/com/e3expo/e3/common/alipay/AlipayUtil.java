package com.e3expo.e3.common.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public class AlipayUtil {
	public static AlipayClient getAlipayClient() {
		AlipayClient client = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,AlipayConfig.merchant_private_key,AlipayConfig.format,AlipayConfig.charset,AlipayConfig.alipay_public_key,AlipayConfig.sign_type);
		return client;
	}
}
