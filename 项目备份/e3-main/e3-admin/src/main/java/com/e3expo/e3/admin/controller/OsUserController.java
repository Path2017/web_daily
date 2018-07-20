package com.e3expo.e3.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.e3expo.e3.admin.controller.rest.RestfulJsonModelAndView;
import com.e3expo.e3.model.OsUser;
import com.e3expo.e3.model.param.OsUserParam;
import com.e3expo.e3.service.interfaces.IOsUser;

/**
 * 用于后台系统用户的相关操作(如用户登录，角色修改，功能分配等)
 * 
 * @author lizy
 *
 */
@Controller
public class OsUserController extends BaseController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private IOsUser iOsUser;

	/**
	 * @api {post} /loginCheck
	 * @apiGroup SystemManage
	 * @apiDescription 登录-登录系统(api)
	 * @apiParam {String} mobile 手机号,必填
	 * @apiParam {String} password 密码,必填
	 * @apiSuccess {int} code 200状态码
	 */
	@PostMapping("/loginCheck")
	public ModelAndView login(OsUserParam param) {
		OsUser model = iOsUser.getOsUser(param);
		if (model != null) {
			addUserSession(model);
		}		
		return RestfulJsonModelAndView.buildJsonModelAndView();
	}

	/**
	 * @api {Get} /logout
	 * @apiGroup SystemManage
	 * @apiDescription 登录-退出系统(link)
	 */
	@GetMapping("/logout")
	public ModelAndView logout() {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;

	}

}
