package com.github.itdachen.framework.log.manager;

import com.github.itdachen.framework.spring.SpringBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Created by 王大宸 on 2021-12-01 17:17
 * Created with IntelliJ IDEA.
 */
public class AsyncManager {

    private static final Logger logger = LoggerFactory.getLogger(AsyncManager.class);
    /**
     * 操作延迟10毫秒
     */
    private static final int OPERATE_DELAY_TIME = 10;

    private static final AsyncManager me = new AsyncManager();

    public static AsyncManager me() {
        return me;
    }


    /**
     * 异步操作任务调度线程池
     */
    private static final ScheduledExecutorService executor = SpringBeanUtils.getBean("scheduledExecutorService");

    /**
     * 单例模式
     */
    private AsyncManager() {
    }


    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        Threads.shutdownAndAwaitTermination(executor);
    }


}
