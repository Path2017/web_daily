package com.e3expo.e3.middleware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.e3expo.e3.middleware.exporter", 
		"com.e3expo.e3.middleware.dao",
		"com.e3expo.e3.middleware.service","com.e3expo.e3.middleware.controller",
		"com.e3expo.e3.middleware.job","com.e3expo.e3.middleware.config.listener",
		"com.e3expo.e3.middleware.common.redis.expiremessage"
})
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public JobFactory getJobFactory() {
		JobFactory jobFactory = new JobFactory();
		return jobFactory;
	}
	
	@Bean
	public SchedulerFactoryBean getScheduler() {
		SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
		factoryBean.setJobFactory(getJobFactory());
		return factoryBean;
	}
	
	
	@Bean
	public RedisMessageListener getRedisListenter() {
		RedisMessageListener listener = new RedisMessageListener();
		return listener;
	}

}
