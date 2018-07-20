package com.e3expo.e3.middleware.common.redis.expiremessage;

import com.e3expo.e3.middleware.common.redis.expiremessage.task.AbstractOrderExpireMessageTask;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class OrderExpireMessageHandler implements Runnable, ApplicationContextAware {

    private Executor executor = Executors.newSingleThreadExecutor();

    @Resource(name = "redisExpireMessageBlockingQueue")
    private BlockingQueue<String> messageQueue;

    private ApplicationContext context;

    @Override
    public void run() {
        try {
            while (true) {
                String expireMessage = messageQueue.take();
                System.out.println("take from queue:" + expireMessage);
                // todo deal with message.

                OrderTask orderTask = resolve(expireMessage);
                executeTask(orderTask);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用executor执行任务
     *
     * @param orderTask
     */
    private void executeTask(OrderTask orderTask) {
        try {
            AbstractOrderExpireMessageTask task = (AbstractOrderExpireMessageTask) context.getBean(Class.forName(orderTask.path));
            task.setOrderId(orderTask.orderId);
            executor.execute(task);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // todo
        }
    }

    /**
     * 将task的类名和orderId解析出来
     *
     * @param expireMessage
     * @return
     */
    private OrderTask resolve(String expireMessage) {
        System.out.println("resolve:" + expireMessage);
        String[] split = expireMessage.split(":");
        if (split.length != 2) {
            throw new IllegalArgumentException("expire.message.illegal.format");
        }
        OrderExpireMessageTypeEnum anEnum = OrderExpireMessageTypeEnum.getByName(split[0]);
        if (anEnum == null) {
            throw new RuntimeException("order.expire.message.name.is.wrong");
        }
        if (split[1] == null) {
            throw new IllegalArgumentException("expire.message.orderId.is.null");
        }
        int orderId = Integer.parseInt(split[1]);
        return new OrderTask(orderId, anEnum.getPath());
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    private class OrderTask {
        final Integer orderId;
        final String path;

        OrderTask(int orderId, String path) {
            this.orderId = orderId;
            this.path = path;
        }
    }
}
