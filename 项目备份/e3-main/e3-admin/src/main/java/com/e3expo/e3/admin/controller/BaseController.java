package com.e3expo.e3.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.e3expo.e3.model.OsUser;


public class BaseController {
	/**
	 * 获取request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();		
		return request;
	}	
	
	/**
	 * 获取登录用户信息
	 */
	public OsUser getUserSession(){
		HttpServletRequest request = getRequest();
		OsUser user = (OsUser)request.getSession().getAttribute("user");
		return user;
	}
	/**
	 * 向session添加用户信息
	 * @param user
	 */
	public void addUserSession(OsUser user) {
		HttpServletRequest request = getRequest();
		request.getSession().setAttribute("user",user);
	}
	/**
	 * 注销该用户的所有登录信息
	 */
	public void delUserSession() {
		HttpServletRequest request = getRequest();
		request.getSession().invalidate();
	}
	
}
