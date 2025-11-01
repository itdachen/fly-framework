package com.github.itdachen.framework.boot.security;

import com.github.itdachen.framework.boot.security.rbac.DefaultRequestAuthorizationHandler;
import com.github.itdachen.framework.boot.security.rbac.IRequestAuthorizationHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RbacAuthorizationHandlerAutoConfiguration
 *
 * @author 王大宸
 * @date 2024-01-10 21:53
 */
@Configuration
public class RbacAuthorizationHandlerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(IRequestAuthorizationHandler.class)
    public IRequestAuthorizationHandler requestAuthorizationHandler() {
        return new DefaultRequestAuthorizationHandler();
    }

}
