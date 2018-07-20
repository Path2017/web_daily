package com.e3expo.e3.middleware.exporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e3expo.e3.middleware.service.TestService;
import com.e3expo.e3.service.interfaces.ITest;


@Component
public class TestExporter implements ITest {
	
	@Autowired
	private TestService service;
	
	@Override
	public String sayHello(String name) {
		service.sayHello(name);
		return "Mike is here:" + name;
		
	}

	

	
}
