package com.e3expo.e3.webapp.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import com.e3expo.e3.webapp.config.cors.CORSFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

/*
 * 该类的作用是位了配置shiro的filter, 用于验证
 * 
 */
public class WebApplicationForShiroInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		// cors
		CORSFilter corsFilter = new CORSFilter();
		FilterRegistration.Dynamic corsFilterDelegate =
				servletContext.addFilter("corsFilter", corsFilter);
		corsFilterDelegate.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");

		// encode filter
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		FilterRegistration.Dynamic encodingFilter =
				servletContext.addFilter("encodingFilter", characterEncodingFilter);
		encodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");


		//其实这里只需要加入filter就可以了
		 FilterRegistration.Dynamic shiroFilter = 
				 servletContext.addFilter("shiroFilter", DelegatingFilterProxy.class);
	        shiroFilter.setInitParameter("targetFilterLifecycle", "true");
	        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
	        

	}

}
