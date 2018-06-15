package com.e3expo.mms.controller.message;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public final class HouseMessageCode implements Serializable {
	//非错误信息的代码
	
	
	public static final int MSG_CODE_USER_IS_EXISTS = 10002; //帐号存在
	public static final int MSG_CODE_LOGIN_SUCCESS = 10003; //登录成功
	public static final int MSG_CODE_UPDATE_PASSWORD_SUCCESS =  10004;  //密码更新成功
	
	

	/**
	 *  私有数据和私有方法
	 */
	private static final long serialVersionUID = 2490584490500691930L;







	
}
