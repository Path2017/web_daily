package com.e3expo.e3.middleware.common.redis.lock;


import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;

/**
 * Redis的自构建锁
 */
public class RedisLock {

    /**
     * 10秒的超时计时
     */
    private static int acquireTimeOut = 1000 * 10;

    /**
     * 获取锁，超时时间10s
     *
     * @param template
     * @param lockName
     * @return
     */
    public static String acquireLock(StringRedisTemplate template, String lockName) {
        String identifier = UUID.randomUUID().toString();
        long end = System.currentTimeMillis() + acquireTimeOut;
        while (System.currentTimeMillis() < end) {
            if (template.opsForValue().setIfAbsent(lockName, identifier)) {
                return identifier;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("failed to connect");
    }

    /**
     * 释放锁
     *
     * @param template
     * @param lockName
     * @param identifier
     * @return
     */
    public static boolean releaseLock(StringRedisTemplate template, String lockName, String identifier) {
        lockName = "lock:" + lockName;
        HashOperations<String, Object, Object> ops = template.opsForHash();
        while (true) {
            String finalLockName = lockName;
            template.executePipelined((RedisCallback<Object>) connection -> {
                StringRedisConnection conn = (StringRedisConnection) connection;
                conn.watch(finalLockName.getBytes());
                if (conn.get(finalLockName).equals(identifier)) {
                    conn.multi();
                    conn.del(finalLockName);
                    conn.exec();
                    return true;
                }
                conn.unwatch();
                return false;
            });
            break;
        }
        return false;
    }
}
