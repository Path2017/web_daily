package com.e3expo.e3.middleware.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.e3expo.e3.middleware.common.RedisUtil;


@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig implements EnvironmentAware {
	

	@Bean("redisExpireMessageBlockingQueue")
	public BlockingQueue<String> redisExpireMessageBlockingQueue() {
		return new LinkedBlockingQueue<>();
	}

	//配置JSR-107的缓存
	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager cacheManager = new RedisCacheManager(getRedisTemplate());
		return cacheManager;
	}
	
	
	
	//这里需要配置Redis
	//配置使用redis服务器
	@Bean
	@Qualifier("JedisConnectionFactory0")
	public JedisConnectionFactory getJedisConnectionFactory() {
		
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(env.getProperty("redis.host"));
		factory.setPort(new Integer(env.getProperty("redis.port")).intValue());
		factory.setPassword(env.getProperty("redis.password"));
		factory.setUsePool(true);
		return factory;
	}

	@Bean
	@Qualifier("JedisConnectionFactory3")
	public JedisConnectionFactory getJedisConnectionFactory3() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(env.getProperty("redis.host"));
		factory.setPort(new Integer(env.getProperty("redis.port")).intValue());
		factory.setPassword(env.getProperty("redis.password"));
		factory.setUsePool(true);
		factory.setDatabase(3);
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

	@Bean
	public RedisUtil getRedisUtil() {
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisFactory3(getJedisConnectionFactory3());
		return redisUtil;
	}



	
}
