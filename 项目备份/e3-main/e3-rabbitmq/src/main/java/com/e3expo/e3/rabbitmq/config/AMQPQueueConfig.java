package com.e3expo.e3.rabbitmq.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.postprocessor.GUnzipPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *  注意在这个子项目里面是主要用于监听队列来处理消息的，
 *  所以创建队列，创建绑定，以及创建exchange这些工作都是需要在e3-middle里面完成
 *  
 *  所以在这里也就不需要定义这些东西，但是在这个项目里需要定义监听器。
 *  
 *  我会定义一个监听器的实例来处理消息
 *  在这个子项目里面没有面对其他的项目的对外接口。
 *  所以所有的定时任务也可以考虑部署在这一层
 * 
 * @author ghw
 *
 */



@Configuration
@PropertySource("classpath:rabbitmq.properties")
public class AMQPQueueConfig implements EnvironmentAware  {
	

	

	/**
	 *  定义主要的监听器
	 * 
	 */
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(CachingConnectionFactory factory) {
		SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory() {
			@Override
			protected SimpleMessageListenerContainer createContainerInstance() {
				SimpleMessageListenerContainer listenerContainer = super.createContainerInstance();
	 
						listenerContainer.setAfterReceivePostProcessors(new GUnzipPostProcessor(true));
	 
				return listenerContainer;
			}
		};
		
		containerFactory.setConnectionFactory(factory);
		containerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
		
		
		return containerFactory;
		
		
	}
	
	
	
	
	/**
	 * 配置rabbitMQ队列连接工厂
	 */
	@Bean
	public CachingConnectionFactory connectionFactory() {
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setHost(env.getProperty("rabbitmq.host"));
		factory.setUsername(env.getProperty("rabbitmq.user"));
		factory.setPassword(env.getProperty("rabbitmq.password"));
		factory.setVirtualHost(env.getProperty("rabbitmq.vhost"));
		factory.setChannelCacheSize(
				new Integer( env.getProperty("rabbitmq.cache.size")).intValue() );
		//如果需要return, 则等于是不能解耦了。
		//factory.setPublisherReturns(true);
		//Confirm方便记录记录重发

		return factory;
	}
	

	
	
	@Override
	public void setEnvironment(Environment arg0) {
		this.env = arg0;
	}
	
	
	
	@Autowired
	private Environment env;


}
