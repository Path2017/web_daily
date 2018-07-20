package com.e3expo.e3.webapp.bean.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledExecutorManager {

    public static ScheduledExecutorService DELETE_VERICODE_IN_SESSION__EXECUTOR =
            Executors.newScheduledThreadPool(5);
}
