package com.github.itdachen.framework.cloud.openfeign.config;

import feign.Feign;
import okhttp3.ConnectionPool;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Description: okhttp 配置 Feign
 * Created by 剑鸣秋朔 on 2023/05/05 20:49
 * Created with IntelliJ IDEA.
 */
@Configuration
// 只有 Feign 存在的时候生效
@ConditionalOnClass(Feign.class)
// 在 FeignAutoConfiguration 初始化之前执行
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignOkHttpConfig {

    private int feignOkHttpReadTimeout = 60;
    private int feignConnectTimeout = 60;
    private int feignWriteTimeout = 120;


    @Bean
    public okhttp3.OkHttpClient okHttpClient() {
        return new okhttp3.OkHttpClient.Builder()
                .readTimeout(feignOkHttpReadTimeout, TimeUnit.SECONDS)
                .connectTimeout(feignConnectTimeout, TimeUnit.SECONDS)
                .writeTimeout(feignWriteTimeout, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(
                        10,
                        5L,
                        TimeUnit.MINUTES))
                .build();
    }

    public int getFeignOkHttpReadTimeout() {
        return feignOkHttpReadTimeout;
    }

    public void setFeignOkHttpReadTimeout(int feignOkHttpReadTimeout) {
        this.feignOkHttpReadTimeout = feignOkHttpReadTimeout;
    }

    public int getFeignConnectTimeout() {
        return feignConnectTimeout;
    }

    public void setFeignConnectTimeout(int feignConnectTimeout) {
        this.feignConnectTimeout = feignConnectTimeout;
    }

    public int getFeignWriteTimeout() {
        return feignWriteTimeout;
    }

    public void setFeignWriteTimeout(int feignWriteTimeout) {
        this.feignWriteTimeout = feignWriteTimeout;
    }


}
