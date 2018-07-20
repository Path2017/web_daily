package com.e3expo.e3.middleware.common;

import com.e3expo.e3.middleware.common.redis.expiremessage.OrderExpireMessageTypeEnum;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component

public class RedisUtil {
	
	
	private JedisConnectionFactory redisFactory3;
	/**
	 * 切换REDIS  db
	 * @param redisTemplate
	 * @param indexDB
	 * @return
	 
	public static <K, V> RedisTemplate<K, V> changeDb(RedisTemplate<K, V> redisTemplate,int indexDB){
		JedisConnectionFactory factory = new JedisConnectionFactory();
		JedisConnectionFactory oldFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
		if(oldFactory.getDatabase() == indexDB) {
			return redisTemplate;
		}
		factory.setHostName(oldFactory.getHostName());
		factory.setPort(oldFactory.getPort());
		factory.setPassword(oldFactory.getPassword());
		factory.setUsePool(true);
		factory.setDatabase(indexDB);
		factory.afterPropertiesSet();
		
		RedisTemplate<K, V> template = new RedisTemplate<K, V>();
		template.setConnectionFactory(factory);
		template.afterPropertiesSet();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}
	
	
	*/
	
	public  <K, V> RedisTemplate<K, V> changeDb3(){
		RedisTemplate<K, V> template = new RedisTemplate<K, V>();
		template.setConnectionFactory(redisFactory3);
		template.afterPropertiesSet();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}
	/**
	 * 切换REDIS  db
	 * @param redisTemplate
	 * @param indexDB
	 * @return
	
	public static StringRedisTemplate changeDb(StringRedisTemplate redisTemplate,int indexDB){
		JedisConnectionFactory factory = new JedisConnectionFactory();
		JedisConnectionFactory oldFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
		if(oldFactory.getDatabase() == indexDB) {
			return redisTemplate;
		}
		factory.setHostName(oldFactory.getHostName());
		factory.setPort(oldFactory.getPort());
		factory.setPassword(oldFactory.getPassword());
		factory.setUsePool(true);
		factory.setDatabase(indexDB);
		factory.afterPropertiesSet();
		StringRedisTemplate template = new StringRedisTemplate();
		
		template.setConnectionFactory(factory);
		template.afterPropertiesSet();
		return template;
	}
 */
	
	public  StringRedisTemplate changeDbStr3(){
		
		StringRedisTemplate template = new StringRedisTemplate();
		
		template.setConnectionFactory(redisFactory3);
		template.afterPropertiesSet();
		return template;
	}
	public JedisConnectionFactory getRedisFactory3() {
		return redisFactory3;
	}
	public void setRedisFactory3(JedisConnectionFactory redisFactory3) {
		this.redisFactory3 = redisFactory3;
	}

	/**
	 * 发送订单定时任务的expireMessage
	 * @param template
	 * @param task
	 * @param orderId
	 * @return
	 */
	public static Boolean sendOrderTaskExpireMessage(StringRedisTemplate template, OrderExpireMessageTypeEnum task, Integer orderId) {
		assert template != null && task != null && orderId != null;
		String key = task.getName() + ":" + orderId;
		System.out.println(key);
		template.opsForValue().set(key, task.getPath());
		return template.expire(key, task.getExpire(), TimeUnit.SECONDS);
	}
	
}
