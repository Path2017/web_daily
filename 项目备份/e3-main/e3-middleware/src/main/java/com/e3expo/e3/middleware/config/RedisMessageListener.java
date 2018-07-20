package com.e3expo.e3.middleware.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.e3expo.e3.middleware.common.RedisUtil;
import com.e3expo.e3.middleware.service.TestService;

import javax.annotation.Resource;

@Component
public class RedisMessageListener implements MessageListener {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "redisExpireMessageBlockingQueue")
    private BlockingQueue<String> messageQueue;

    private RedisSerializer serializer;

//    private static final String LOCK = "task-job-lock";
//
//    private static final String KEY = "tasklock";

//    @Autowired
//    private TestService testService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onMessage(Message message, byte[] pattern) {
//		boolean lock = false;
//		lock = redisTemplate.opsForValue().setIfAbsent(KEY, LOCK);
//		if(lock) {
//			redisTemplate.expiremessage(KEY, 200, TimeUnit.MILLISECONDS);

        serializer = redisTemplate.getValueSerializer();
        String messageStr = (String) stringRedisTemplate.getValueSerializer().deserialize(message.getBody());
        String channel = stringRedisTemplate.getStringSerializer().deserialize(message.getChannel());
        StringRedisTemplate stringRedis = redisUtil.changeDbStr3();
        stringRedis.opsForHash().put("mmm", "ee", "111");
        System.out.println("channel:" + channel + "message received:" + messageStr);
        try {
            // put into bq
            messageQueue.put(messageStr);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//		}else {
//			System.out.println("没有获取到锁，不执行！");
//		}
//		if(lock) {
//			redisTemplate.delete(KEY);
//		}
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


}
