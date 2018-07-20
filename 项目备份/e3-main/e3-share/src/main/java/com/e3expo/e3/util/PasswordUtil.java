package com.e3expo.e3.util;


public class PasswordUtil {
	
	public final static String PASSWORD_STR="Jsl5eyA";
	
	public static String encryptPassword(String password){
		String result = null;
		result = EncodeUtil.SHA1(EncodeUtil.MixEncode(PASSWORD_STR+password)).toUpperCase();
		
		return result;
	}
}
