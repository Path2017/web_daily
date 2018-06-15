package com.e3expo.mms.controller.message;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:message.properties")
public final class HouseErrorMessage implements Serializable  {

	//用户不存在
	public static String ERR_STRING_UESR_IS_NOT_EXISTS; //用户不存在
	public static String ERR_STRING_PASSWORD_INCORRECT; //登录密码不正确 
	public static String ERR_STRING_LOGIN_FAILED; //登录失败
	public static String ERR_STRING_USER_ALREADY_LOGIN; //用户已经登录
	
	
	
	
	
	
	@Autowired
	public void setEnvironment(Environment environment) {
		env = environment;
		ERR_STRING_UESR_IS_NOT_EXISTS = env.getProperty("err_string_user_is_not_exists");
		ERR_STRING_PASSWORD_INCORRECT = env.getProperty("err_string_password_incorrect");
		ERR_STRING_LOGIN_FAILED = env.getProperty("err_string_login_failed");
		ERR_STRING_USER_ALREADY_LOGIN = env.getProperty("err_string_user_already_login");
		
	}
	
	/**
	 * 
	 */
	private Environment env;
	
	private static final long serialVersionUID = 1892351693862998824L;




	

}
