package com.github.itdachen.framework.boot.oplog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程配置
 *
 * @author 王大宸
 * @date 2025-09-09 16:32
 */
public class OplogThreadPoolTaskConfig {

    // 低并发，IO耗时长的策略
    @Bean(name = "myExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 较大核心线程数
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(100);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(30);
        // 设置线程池内线程名称的前缀
        executor.setThreadNamePrefix("IO-threadPool-");
        //设置任务的拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //初始化
        executor.initialize();
        return executor;
    }

    // 高并发，耗时短的策略
    @Bean(name = "concurrencyExecutor")
    public ThreadPoolTaskExecutor concurrencyExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 小核心线程数
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setKeepAliveSeconds(2);
        // 大阻塞队列
        executor.setQueueCapacity(1000);
        // 设置线程池内线程名称的前缀
        executor.setThreadNamePrefix("concurrency-threadPool-");
        // 设置任务的拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }


}
