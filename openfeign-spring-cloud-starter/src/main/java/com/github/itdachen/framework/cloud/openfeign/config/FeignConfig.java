package com.github.itdachen.framework.cloud.openfeign.config;

import com.github.itdachen.framework.context.constants.UserInfoConstant;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Description: OpenFeign 配置类
 * Created by 王大宸 on 2023/05/05 20:48
 * Created with IntelliJ IDEA.
 */
@Configuration
public class FeignConfig {

    /* 连接超时时间 */
    public static final int CONNECT_TIMEOUT_MILLS = 5000;
    /* 读取响应结果的超时时间 */
    public static final int READ_TIMEOUT_MILLS = 5000;

    /***
     * 开启 OpenFeign 日志
     *  Logger.Level.NONE:    不记录任何日志
     *  Logger.Level.BASIC:   紧记录请求方法,url,响应状态码及执行时间
     *  Logger.Level.HEADERS:
     *  Logger.Level.FULL:    最相信信息
     * @author 王大宸
     * @date 2021/8/26 21:51
     * @return feign.Logger.Level
     */
    @Bean
    public Logger.Level feignLogger() {
        //  需要注意, 日志级别需要修改成 debug
        return Logger.Level.FULL;
    }

    /***
     * 开启重试
     * period = 100 发起当前请求的时间间隔, 单位是 ms
     * maxPeriod = 1000 发起当前请求的最大时间间隔, 单位是 ms
     * maxAttempts = 5 最多请求次数
     * @author 王大宸
     * @date 2021/8/26 21:43
     * @return feign.Retryer
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(
                100,
                SECONDS.toMillis(1),  // 秒 转换成 毫秒
                5
        );
    }

    /***
     * 对请求的连接和响应时间进行限制
     *
     * @author 王大宸
     * @date 2021/8/26 21:42
     * @return feign.Request.Options
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(
                CONNECT_TIMEOUT_MILLS,
                TimeUnit.MICROSECONDS,  // 指定单位是 毫秒
                READ_TIMEOUT_MILLS,
                TimeUnit.MILLISECONDS,
                true // 是否转发也做限制
        );
    }

    /***
     * 给 Feign 配置请求拦截器, Feign 调用时, 把 Header 也传递到服务提供方
     * RequestInterceptor 是我们提供给 open-feign 的请求拦截器, 把 Header 信息传递
     * @author 王大宸
     * @date 2021/9/22 14:04
     * @return feign.RequestInterceptor
     */
    @Bean
    public RequestInterceptor headerInterceptor() {
        return template -> {
            template.header(UserInfoConstant.NAL_FEIGN, UserInfoConstant.NAL_FEIGN_VALUE);
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (null != attributes) {
                String name = null;
                String values = null;
                HttpServletRequest request = attributes.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (null != headerNames) {
                    while (headerNames.hasMoreElements()) {
                        name = headerNames.nextElement();
                        values = request.getHeader(name);
                        // 不能把当前请求的 content-length 传递到下游的服务提供方, 这明显是不对的
                        // 请求可能一直返回不了, 或者是请求响应数据被截断
                        if (!name.equalsIgnoreCase("content-length")) {
                            // 这里的 template 就是 RestTemplate
                            template.header(name, values);
                        }
                    }
                }
            }
        };
    }


}
