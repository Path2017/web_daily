package com.e3expo.e3.admin.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {
	
	private static  ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		// TODO Auto-generated method stub
		ApplicationContextUtil.applicationContext = applicationContext;
	}
	
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	public static Object getBean(String name){
		return applicationContext.getBean(name);
	}
}
