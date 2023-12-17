package com.github.itdachen.framework.security.rbac;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 默认权限配置
 *
 * @author 王大宸
 * @date 2023/11/27 20:28
 */
@Configuration
public class RbacAuthorizationConfiguration {

    @Bean
    @ConditionalOnMissingBean(IRequestAuthorizationHandler.class)
    public IRequestAuthorizationHandler requestAuthorizationHandler() {
        return new DefaultRequestAuthorizationHandler();
    }


}
