package com.e3expo.e3.middleware.config.listener;

import com.e3expo.e3.middleware.common.redis.expiremessage.OrderExpireMessageHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 项目启动时的监听器
 */
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware{

    private static Executor executor = Executors.newSingleThreadExecutor();
    private static boolean redisExpireMessageConsumerThreadFlag = false;
    private ApplicationContext context;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 启动线程
        if (!redisExpireMessageConsumerThreadFlag) {
            // 防止重复启动
            executor.execute(context.getBean(OrderExpireMessageHandler.class));
            redisExpireMessageConsumerThreadFlag = true;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
