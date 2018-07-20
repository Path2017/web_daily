package com.e3expo.e3.rabbitmq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig implements EnvironmentAware {
	
	
	//这里需要配置Redis
	//配置使用redis服务器
	@Bean
	public JedisConnectionFactory getJedisConnectionFactory() {
		
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(env.getProperty("redis.host"));
		factory.setPort(new Integer(env.getProperty("redis.port")).intValue());
		factory.setPassword(env.getProperty("redis.password"));
		factory.setUsePool(true);
		return factory;
	}
	
	
	
	@Bean
	public <K, V> RedisTemplate<K, V> getRedisTemplate() {
		RedisTemplate<K, V> template = new RedisTemplate<K, V>();
		template.setConnectionFactory(getJedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}
	
	
	@Bean
	public StringRedisTemplate getStringRedisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(getJedisConnectionFactory());
		
		return template;
		
	}
	
	
	
	@Override
	public void setEnvironment(Environment arg0) {
		this.env = arg0;
	}
	
	
	

	@Autowired
	private Environment env;






	
}
