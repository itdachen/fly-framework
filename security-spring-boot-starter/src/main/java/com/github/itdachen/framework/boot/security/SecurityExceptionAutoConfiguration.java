package com.github.itdachen.framework.boot.security;

import com.github.itdachen.framework.boot.autoconfigure.security.properties.session.SecuritySessionProperties;
import com.github.itdachen.framework.boot.security.handler.ClientSessionExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常配置
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 1:04
 */
@Configuration
@RestControllerAdvice(basePackages = {"${fly.client.listen-package:com.github.itdachen}"})
public class SecurityExceptionAutoConfiguration {

    private final SecuritySessionProperties sessionProperties;

    public SecurityExceptionAutoConfiguration(SecuritySessionProperties sessionProperties) {
        this.sessionProperties = sessionProperties;
    }

    /***
    * 异常统一处理
    *
    * @author 剑鸣秋朔
    * @date 2023/12/23 1:09
    * @return com.github.itdachen.boot.security.handler.ClientSessionExceptionHandler
    */
    @Bean
    public ClientSessionExceptionHandler clientSessionExceptionHandler(){
        return new ClientSessionExceptionHandler(sessionProperties);
    }

}
