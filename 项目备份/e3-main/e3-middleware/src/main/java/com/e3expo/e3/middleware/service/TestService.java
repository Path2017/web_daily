package com.e3expo.e3.middleware.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayClient;

@Service
public class TestService {
	
	
	@Cacheable("hello")
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		System.out.println("被调用了!");
		return "This is Mike Guo and " + name;
		
	}
	

}
