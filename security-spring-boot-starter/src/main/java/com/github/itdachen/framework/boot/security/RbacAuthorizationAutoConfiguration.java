package com.github.itdachen.framework.boot.security;

import com.github.itdachen.framework.boot.security.rbac.IRequestAuthorizationHandler;
import com.github.itdachen.framework.boot.security.rbac.RbacRequestAuthorizationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/***
 * 默认权限配置
 *
 * @author 王大宸
 * @date 2023/11/27 20:28
 */
@Configuration
public class RbacAuthorizationAutoConfiguration {

    private final IRequestAuthorizationHandler requestAuthorizationHandler;
    public RbacAuthorizationAutoConfiguration(@Lazy IRequestAuthorizationHandler requestAuthorizationHandler) {
        this.requestAuthorizationHandler = requestAuthorizationHandler;
    }

    @Bean
    public RbacRequestAuthorizationManager rbacRequestAuthorizationManager() {
        return new RbacRequestAuthorizationManager(requestAuthorizationHandler);
    }


}
