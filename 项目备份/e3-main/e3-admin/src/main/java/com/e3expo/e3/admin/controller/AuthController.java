package com.e3expo.e3.admin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.e3expo.e3.model.Rfp;
import com.e3expo.e3.model.User;
import com.e3expo.e3.model.form.FileFormat;
import com.e3expo.e3.service.interfaces.IUtil;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;

/**
 * 登录验证相关
 * @author lizy
 *
 */
@Controller
public class AuthController {
	@GetMapping("/login")
	public ModelAndView login() {
	    ModelAndView modelAndView=new ModelAndView("login");
		return modelAndView;
	}
	@GetMapping("/index")
	public ModelAndView index() {
	    ModelAndView modelAndView=new ModelAndView("index");
		return modelAndView;
	}
	@GetMapping("/manageDesigner")
	public ModelAndView manageDesigner() {
		 ModelAndView modelAndView=new ModelAndView("manage_designer");
			return modelAndView;
	}
	@GetMapping("/manageExhibition")
	public ModelAndView manageExhibition() {
		 ModelAndView modelAndView=new ModelAndView("manage_exhibition");
			return modelAndView;
	}
	@GetMapping("/verifyCheckInList")
	public ModelAndView verifyCheckInList() {
		 ModelAndView modelAndView=new ModelAndView("verify_checkin_list");
			return modelAndView;
	}
	@GetMapping("/exhibitionCheckInList")
	public ModelAndView exhibitionCheckInList() {
		 ModelAndView modelAndView=new ModelAndView("exhibition_checkin_list");
			return modelAndView;
	}
	@GetMapping("/manageOrder")
	public ModelAndView manageOrder() {
		 ModelAndView modelAndView=new ModelAndView("manage_order");
			return modelAndView;
	}
	@GetMapping("/financialEntryFlow")
	public ModelAndView financialEntryFlow() {
		 ModelAndView modelAndView=new ModelAndView("financial_entry_flow");
			return modelAndView;
	}
	@GetMapping("/financialDeduction")
	public ModelAndView financialDeduction() {
		 ModelAndView modelAndView=new ModelAndView("financial_deduction");
			return modelAndView;
	}
	@GetMapping("/settlementDesigner")
	public ModelAndView settlementDesigner() {
		 ModelAndView modelAndView=new ModelAndView("settlement_designer");
			return modelAndView;
	}
	
}
