package com.e3expo.e3.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.e3expo.e3.service.interfaces.ITest;

//@Service
public class MakeSureWorkService {
	
	@Cacheable(cacheNames="hello")
	public String getHelloName() {
		
		System.out.println("我被调用了!");
		
		return test.sayHello("我是中国人");
		
	}
	
	
	@Autowired
	private ITest test;
	

}
