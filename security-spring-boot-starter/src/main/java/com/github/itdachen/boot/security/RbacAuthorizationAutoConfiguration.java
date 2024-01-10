package com.github.itdachen.boot.security;


import com.github.itdachen.boot.security.rbac.DefaultRequestAuthorizationHandler;
import com.github.itdachen.boot.security.rbac.IRequestAuthorizationHandler;
import com.github.itdachen.boot.security.rbac.RbacRequestAuthorizationManager;
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
public class RbacAuthorizationAutoConfiguration {

    private final IRequestAuthorizationHandler requestAuthorizationHandler;

    public RbacAuthorizationAutoConfiguration(IRequestAuthorizationHandler requestAuthorizationHandler) {
        this.requestAuthorizationHandler = requestAuthorizationHandler;
    }

    @Bean
    public RbacRequestAuthorizationManager rbacRequestAuthorizationManager() {
        return new RbacRequestAuthorizationManager(requestAuthorizationHandler);
    }


}
