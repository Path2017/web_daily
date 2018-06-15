package com.e3expo.mms.controller.message;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public final class HouseErrorCode  implements Serializable {

	/**
	 * 定义错误的信息的代码
	 */
	//用户帐号不存在
	public static final int ERR_CODE_USER_IS_NOT_EXISTS = 20001;  //用户帐号不存在
	public static final int ERR_CODE_PASSWORD_INCORRENT = 20002; //密码错误
	public static final int ERR_CODE_LOGIN_FAILED =  20003;		//登录失败
	public static final int ERR_CODE_USER_ALREADY_LOGIN = 20004;  //用户已经登录
	
	
	
	/**
	 *  私有数据和私有方法
	 */
	private static final long serialVersionUID = -8331774010964958008L;






	


	
}
