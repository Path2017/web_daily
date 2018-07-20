package com.e3expo.e3.middleware.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {
			RootConfig.class,
			AMQPQueueConfig.class,
			RedisConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class<?>[] { MvcConfig.class, RemoteService.class,RedisMessageListenerContainerConfig.class };
		
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
