package com.github.itdachen.framework.security.log.management;

import com.github.itdachen.framework.security.log.IAuthFailureCredentialsLogHandler;
import com.github.itdachen.framework.security.log.IAuthSuccessCredentialsLogHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 默认记录方式, 打印日志
 * Created by 王大宸 on 2023-07-09 14:31
 * Created with IntelliJ IDEA.
 */
@Configuration
public class AuthLogBeanConfiguration {

    /***
     * 指定默认登录失败记录方法
     *
     * @author 王大宸
     * @date 2023/7/9 14:34
     * @return com.github.itdachen.framework.security.log.IAuthFailureCredentialsLogHandler
     */
    @Bean
    @ConditionalOnMissingBean(IAuthFailureCredentialsLogHandler.class)
    public IAuthFailureCredentialsLogHandler authFailureCredentialsLogHandler() {
        return new AuthFailureLogHandlerManagement();
    }

    /***
     * 指定默认登录成功记录方式
     *
     * @author 王大宸
     * @date 2023/7/9 14:34
     * @return com.github.itdachen.framework.security.log.IAuthSuccessCredentialsLogHandler
     */
    @Bean
    @ConditionalOnMissingBean(IAuthSuccessCredentialsLogHandler.class)
    public IAuthSuccessCredentialsLogHandler authSuccessCredentialsLogHandler() {
        return new AuthSuccessLogHandlerManagement();
    }


}
