package com.e3expo.e3.rabbitmq.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {
			RootConfig.class,
			RedisConfig.class,
			AMQPQueueConfig.class
			
		};
	
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {
			MvcConfig.class
		};
		
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
