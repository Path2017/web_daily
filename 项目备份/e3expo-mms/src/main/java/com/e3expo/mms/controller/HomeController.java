package com.e3expo.mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends ParentController {

	@RequestMapping({"/index", "/"})
	public ModelAndView getRootPath(ModelAndView mov) throws Exception {
		mov.setViewName("redirect:/design/list");
		return mov;
	}
	
}
