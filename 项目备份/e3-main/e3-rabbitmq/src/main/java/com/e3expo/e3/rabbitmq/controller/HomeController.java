package com.e3expo.e3.rabbitmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String home() throws Exception {
		
		return "This is The E3 rabbitmq home page";
	}

}
