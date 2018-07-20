package com.e3expo.e3.admin.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {MvcConfig.class, RemoteClient.class};
	}

	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {  RootConfig.class};
	}

	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	
	@Override
	//处理表单post会乱码的问题, spring mvc
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addFilter("name", new CharacterEncodingFilter("UTF-8", true)).addMappingForUrlPatterns(null,
				false, "/*");
	}

}
