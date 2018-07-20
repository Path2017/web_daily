package com.e3expo.e3.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.e3expo.e3.model.User;

@Controller
public class Test {
	@ResponseBody
	@GetMapping("/test1")
	public User test1() {
		User user=new User();
		user.setName("hahha");
		return user;
	}

}
