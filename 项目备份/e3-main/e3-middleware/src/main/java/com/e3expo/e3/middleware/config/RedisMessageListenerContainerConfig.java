package com.e3expo.e3.middleware.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public  class RedisMessageListenerContainerConfig {
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private RedisMessageListener redisListener;
	
	@Bean
	public RedisMessageListenerContainer config() {
		RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
		listenerContainer.setConnectionFactory(redisTemplate.getConnectionFactory());
		ChannelTopic topic = new ChannelTopic("__keyevent@0__:expired");
		listenerContainer.addMessageListener(redisListener, topic);
		listenerContainer.setTaskExecutor(getTaskPool());
		return listenerContainer;
		
	}
	
	
	@Bean
	public Executor getTaskPool() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(10);
		executor.setKeepAliveSeconds(60);
		executor.setThreadNamePrefix("myThreadPool");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}
}
