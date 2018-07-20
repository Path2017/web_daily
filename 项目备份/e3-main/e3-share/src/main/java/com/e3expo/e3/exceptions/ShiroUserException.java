package com.e3expo.e3.exceptions;

import com.e3expo.e3.enumration.EnumAuditStatus;

public class ShiroUserException extends ValidateRunTimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3234056978978368691L;
	
	
	public static enum ErrorCode implements BaseErrorCode{
		
		//密码错误
		USER_PASSWORD_ERROR(200001,"User.password.error"),
		
		//手机号不合法
		USER_MOBILE_INVALID(200002,"User.mobile.invalid"),
		
		//用户名不能为空
		USER_USERNAME_NULL(200003,"User.username.is.null"),
		
		//密码不能为空
		USER_PASSWORD_NULL(200004,"User.password.is.null"),
		
		//密码不合法
		USER_PASSWORD_INVALID(200005,"User.password.is.invaild"),
		
		//用户审核中
		USER_NOT_AUDIT(200006,"User.is.auditing"),
		
		//用户没有登录
		USER_NO_LOGIN(200007,"User.is.not.login"),
		
		//用户被停用
		USER_IS_STOP(200008,"User.account.is.stop"),
		
		//手机号码不存在
		USER_MOBILE_NOT_EXSIT(200010,"User.mobile.is.not.exsit"),
		
		//验证码发送错误
		USER_SEND_CODE_ERROR(200011,"User.send.code.error"),
		
		//验证码不匹配
		USER_CHECK_CODE_NO_MATE(200012,"User.check.out.not.mate"),
		
		//手机号码和发送验证码的手机号码不匹配
		USER_MOBLE_NOT_MATE(200013,"User.mobile.and.checkcode.not.mate"),
		//手机号码为空
		USER_MOBILE_IS_NULL(200014,"User.mobile.is.null"),
		
		//验证码为空
		USER_CHECK_CODE_IS_NULL(200015,"User.checkcode.is.null"),
		
		//验证码失效
		USER_CODE_INVALID(200017,"User.code.is.invalid")
		;
		
		private int value;
		
		private String desc;

		private ErrorCode(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}
		
		public static ErrorCode getType(int value){
			for(ErrorCode type : ErrorCode.values()){
				if(type.value==value){
					return type;
				}
			}
			return null;
		}
	}
	
	public ShiroUserException(){
		super();
	}
	
	
	public ShiroUserException(BaseErrorCode code){
		super();
	}

}
