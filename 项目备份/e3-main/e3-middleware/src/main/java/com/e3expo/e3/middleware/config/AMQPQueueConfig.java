package com.e3expo.e3.middleware.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.postprocessor.GZipPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:rabbitmq.properties")
public class AMQPQueueConfig implements EnvironmentAware  {
	
	/**
	 *  这里定义Template, Exchange, 队列， 注意所有的routekey都使用队列的名称
	 * 
	 */

	/**
	 * 定义新老系统注册交互的队列，
	 * 发送的消息是Form表单
	 */
	@Bean
	@Qualifier("register_old")
	public RabbitTemplate registerOldRabbitTemplate(CachingConnectionFactory factory) {
		RabbitTemplate template = new RabbitTemplate(factory);
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		template.setBeforePublishPostProcessors(new GZipPostProcessor());
		template.setQueue(env.getProperty("queue.register.old"));
		template.setExchange(env.getProperty("exchange.register.old"));
		template.setRoutingKey(env.getProperty("queue.register.old"));
		return template;
	}	
	
//	@Bean
//	@Qualifier("register_oldtest")
//	public RabbitTemplate registerOldRabbitT(CachingConnectionFactory factory) {
//		RabbitTemplate template = new RabbitTemplate(factory);
//		template.setMessageConverter(new Jackson2JsonMessageConverter());
//		template.setBeforePublishPostProcessors(new GZipPostProcessor());
//		template.setQueue(env.getProperty("queue.register.old"));
//		template.setExchange(env.getProperty("exchange.register.old"));
//		template.setRoutingKey(env.getProperty("queue.register.old"));
//		return template;
//	}	
//
	@Bean
	@Qualifier("registerOldQueue")
	public Queue registerOldQueue() {
		return new Queue(env.getProperty("queue.register.old"));
	}
	
	@Bean
	@Qualifier("registerOldExchange")
	public DirectExchange registerOldExchange() {
		return new DirectExchange(env.getProperty("exchange.register.old"));
	}
	
//	@Bean
//	@Qualifier("registerTopicExchange")
//	public TopicExchange registerTopicExchange() {
//		return new TopicExchange(env.getProperty("exchange.register.old.test"));
////		return new DirectExchange(env.getProperty("exchange.register.old.test"));
//	}
	
	@Bean
	@Qualifier("registerOldBinding")
	public Binding registerOldBinding() {
		return BindingBuilder.bind(testQueue()).to(testExchange()).with(env.getProperty("queue.register.old"));
	}
	
	
	
	
	
	
	/**
	 * 测试队列
	 */
	@Bean
	@Qualifier("test")
	public RabbitTemplate testRabbitTemplate(CachingConnectionFactory factory) {
		RabbitTemplate template = new RabbitTemplate(factory);
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		template.setBeforePublishPostProcessors(new GZipPostProcessor());
		template.setQueue(env.getProperty("queue.test"));
		template.setExchange(env.getProperty("exchange.test"));
		template.setRoutingKey(env.getProperty("queue.test"));
		return template;
	}
	
	@Bean
	@Qualifier("testQueue")
	public Queue testQueue() {
		return new Queue(env.getProperty("queue.test"));
	}
	
	@Bean
	@Qualifier("testExchange")
	public DirectExchange testExchange() {
		return new DirectExchange(env.getProperty("exchange.test"));
	}
	
	@Bean
	@Qualifier("testBinding")
	public Binding testBinding() {
		return BindingBuilder.bind(testQueue()).to(testExchange()).with(env.getProperty("queue.test"));
	}
	
	//~~ 测试队列绑定完毕
	
	
	
	
	
	
	
	
	
	
	/**----------------------------------------------------------------
	 *  消息队列的基础发布区域
	 *----------------------------------------------------------------*/
	
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
	
	/**
	 * 配置admin, 用于queue和exchange 重建
	 */
	@Bean
	public RabbitAdmin getRabbitAdmin(CachingConnectionFactory factory) {
		
		return new RabbitAdmin(factory);
	}
	
	
	@Override
	public void setEnvironment(Environment arg0) {
		this.env = arg0;
	}
	
	
	
	@Autowired
	private Environment env;


}
