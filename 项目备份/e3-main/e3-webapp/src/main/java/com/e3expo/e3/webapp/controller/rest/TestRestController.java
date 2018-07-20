package com.e3expo.e3.webapp.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e3expo.e3.service.interfaces.ITest;
import com.e3expo.e3.webapp.services.TestService;

@RestController
public class TestRestController {
	
	@Autowired
	private ITest test;
	@GetMapping("/testrest")
	public String doTest() throws Exception {
		test.sayHello("");
		return new String("mike");
		
	}

}
