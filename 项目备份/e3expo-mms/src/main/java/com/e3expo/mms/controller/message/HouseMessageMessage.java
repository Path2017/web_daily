package com.e3expo.mms.controller.message;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:message.properties")
public class HouseMessageMessage implements Serializable  {

	public static String MSG_STRING_USER_IS_EXISTS;
	public static String MSG_STRING_LOGIN_SUCCESS;
	public static String MSG_STRING_UPDATE_PASSWORD_SUCCESS;
	
	
	
	
	
	
	@Autowired
	public void setEnviroment(Environment environment) {
		env = environment;

		//用户帐号存在
		MSG_STRING_USER_IS_EXISTS = env.getProperty("msg_string_user_is_exists");
		//登录成功
		MSG_STRING_LOGIN_SUCCESS = env.getProperty("msg_string_login_success");
		MSG_STRING_UPDATE_PASSWORD_SUCCESS = env.getProperty("msg_string_update_password_success");
		
	}
	
	
	
	/**
	 *  私有数据和私有方法
	 */
	
	private Environment env;
	private static final long serialVersionUID = 7622139085705453371L;

	
	
}
