package com.e3expo.mms.controller.admin;

import java.util.ArrayList;

import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.service.UserService;
import com.e3expo.mms.controller.ParentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserAdminController extends ParentController {
	
	
	@GetMapping("/user")
	public String getUserList(Model model) throws Exception {
		ArrayList<User> users = userService.getUserListByPage(1);
		int totalPage = userService.getUserTotalPage();
		
		
		model.addAttribute("userList", users);
		model.addAttribute("page", 1);
		model.addAttribute("totalPage", totalPage);
		
		return "admin/user-list";
		
	}

	
	@GetMapping("/user/{page}")
	public String getUserListByPage(
			@PathVariable int page,
			Model model ) throws Exception {
		
		int totalPage = userService.getUserTotalPage();
		
		if ( page <= 0 ) {
			page = 1;
		}
		
		if ( page > totalPage && totalPage > 0 ) {
			page = totalPage;
		}
		
		ArrayList<User> users = userService.getUserListByPage(page);
		
		model.addAttribute("userList", users);
		model.addAttribute("page", 1);
		model.addAttribute("totalPage", totalPage);
		
		
		return "admin/user-list";
		
	}
	
	
	@Autowired
	private UserService userService;
	

}
