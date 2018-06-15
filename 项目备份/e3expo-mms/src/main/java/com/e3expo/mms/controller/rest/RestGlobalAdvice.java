package com.e3expo.mms.controller.rest;


import com.e3expo.mms.Exception.AccountIsNotExistsException;
import com.e3expo.mms.controller.message.HouseError;
import com.e3expo.mms.controller.message.HouseErrorCode;
import com.e3expo.mms.controller.message.HouseErrorMessage;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.e3expo.mms.Exception.UserAlreadyLoginException;

@RestControllerAdvice
public class RestGlobalAdvice {
	
	//帐号不存在
//	@ExceptionHandler({AccountIsNotExistsException.class,
//		UnknownAccountException.class
//	})
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public HouseError accountIsNotExists(AccountIsNotExistsException e) {
//
//		return new HouseError(HouseErrorCode.ERR_CODE_USER_IS_NOT_EXISTS,
//				HouseErrorMessage.ERR_STRING_UESR_IS_NOT_EXISTS
//				);
//
//	}
	
	//密码错误
//	@ExceptionHandler(IncorrectCredentialsException.class)
//	@ResponseStatus(HttpStatus.FORBIDDEN)
//	public HouseError passwordIsNotCorrent(IncorrectCredentialsException e) {
//
//		return new HouseError(HouseErrorCode.ERR_CODE_PASSWORD_INCORRENT,
//				HouseErrorMessage.ERR_STRING_PASSWORD_INCORRECT
//				);
//	}
//
	
	//登录失败
//	@ExceptionHandler(AuthenticationException.class)
//	@ResponseStatus(HttpStatus.BAD_GATEWAY)
//	public HouseError badLogin(AuthenticationException e) {
//
//		return new HouseError(HouseErrorCode.ERR_CODE_LOGIN_FAILED,
//				HouseErrorMessage.ERR_STRING_LOGIN_FAILED
//				);
//	}
	
//	//用户已经登录
//	@ExceptionHandler(UserAlreadyLoginException.class)
//	@ResponseStatus(HttpStatus.CONFLICT)
//	public HouseError alreadyLogin(UserAlreadyLoginException e) {
//
//		return new HouseError(HouseErrorCode.ERR_CODE_USER_ALREADY_LOGIN,
//				HouseErrorMessage.ERR_STRING_USER_ALREADY_LOGIN
//				);
//
//	}
	
	
	
	
	
	
	
	
	
	
	
	

	

}
