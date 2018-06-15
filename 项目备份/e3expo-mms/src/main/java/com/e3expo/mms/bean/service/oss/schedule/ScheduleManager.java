package com.e3expo.mms.bean.service.oss.schedule;


import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 */
public class ScheduleManager {

    private static final ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

    private static final HashMap<Integer, ScheduledFuture> futureMap = new HashMap<>();

    public static void schedule(Runnable task, long delay, TimeUnit timeUnit) {
        ScheduledFuture future = service.schedule(task, delay, timeUnit);
    }



    public static synchronized void cancel(Integer key){
        ScheduledFuture future = futureMap.get(key);
        if (future == null)       return;
        if (future.isDone())      return;
        if (future.isCancelled()) return;
        future.cancel(false);
        futureMap.remove(key);
    }

    private static synchronized void addToFutureMap(Integer batchID, ScheduledFuture future) {
        futureMap.put(batchID, future);
    }

    public static synchronized void removeFromFutureMap(Integer key) {
        futureMap.remove(key);
    }

    /**
     * 立刻执行任务
     * @param task 任务
     */
    public static void schedule(Runnable task) {
        schedule(task, 50, TimeUnit.MICROSECONDS);
    }
}
