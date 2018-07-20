package com.e3expo.e3.webapp.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3expo.e3.webapp.services.MakeSureWorkService;

//@Controller
public class HomeController {

	
//	@GetMapping("/")
	public String home(Model model) throws Exception {
		
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		session.setAttribute("mike", "ghw");
		
		String nameString = (String)session.getAttribute("mike");
		
		System.out.println(nameString);
		
		model.addAttribute("message",  workService.getHelloName());
		
		return "test";
		
	}
	
	
	
//	@GetMapping("/login")
	public String login() throws Exception {
		
		return "login";
		
	}
	
//	@GetMapping("/logout")
	public String Logout() throws Exception {
		
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		
		return "redirect:/login";
		
	}
	
	
	@PostMapping("/login-submit")
	public String submitLogin(@RequestParam("username") String username, 
			@RequestParam("password") String password) throws Exception {
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		
		if ( subject.isAuthenticated() ) {
			System.out.println("ok");
		} else {
			System.out.println("Failed");
		}
		
		
		return "login-success";
		
	}
	

	
	@Autowired
	private MakeSureWorkService workService;
	
	
	
}
