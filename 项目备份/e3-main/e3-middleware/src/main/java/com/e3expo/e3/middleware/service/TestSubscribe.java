package com.e3expo.e3.middleware.service;

import org.springframework.stereotype.Component;

import com.e3expo.e3.middleware.config.MessagePubSub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Component
public class TestSubscribe {
	
	public void subMessage() {
		Jedis jedis = new Jedis("");
		MessagePubSub listener = new MessagePubSub();
//		jedis.subscribe(jedisPubSub, channels);
	}
	
}
