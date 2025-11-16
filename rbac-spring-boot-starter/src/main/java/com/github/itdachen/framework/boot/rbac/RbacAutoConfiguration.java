package com.github.itdachen.framework.boot.rbac;

import com.github.itdachen.framework.boot.rbac.cache.IPermissionCacheService;
import com.github.itdachen.framework.boot.rbac.permission.RbacPermissionServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RbacAutoConfiguration
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 0:23
 */
@Configuration
public class RbacAutoConfiguration {

    private final IPermissionCacheService permissionCacheService;

    public RbacAutoConfiguration(IPermissionCacheService permissionCacheService) {
        this.permissionCacheService = permissionCacheService;
    }

    @Bean
    @ConditionalOnMissingBean(IRbacPermissionService.class)
    public IRbacPermissionService rbacPermissionService() {
        return new RbacPermissionServiceImpl(permissionCacheService);
    }


}
