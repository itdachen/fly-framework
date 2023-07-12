package com.github.itdachen.framework.threads.manager;

import com.github.itdachen.framework.spring.SpringBeanUtils;
import com.github.itdachen.framework.threads.constants.ThreadPoolTaskConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description: 异步线程管理
 * Created by 王大宸 on 2023-07-12 15:12
 * Created with IntelliJ IDEA.
 */
public class AsyncThreadsManager {
    private static final Logger logger = LoggerFactory.getLogger(AsyncThreadsManager.class);
    /**
     * 操作延迟10毫秒
     */
    private static final int OPERATE_DELAY_TIME = 10;

    private static final AsyncThreadsManager me = new AsyncThreadsManager();

    /**
     * 异步操作任务调度线程池
     */
    private static final ScheduledExecutorService executor = SpringBeanUtils.getBean(ThreadPoolTaskConstant.THREAD_POOL_NAME);


    public static AsyncThreadsManager me() {
        return me;
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
