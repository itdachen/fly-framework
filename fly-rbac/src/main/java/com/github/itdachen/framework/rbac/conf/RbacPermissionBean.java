package com.github.itdachen.framework.rbac.conf;

import com.github.itdachen.framework.rbac.cache.IPermissionCacheService;
import com.github.itdachen.framework.rbac.permission.RbacPermissionServiceImpl;
import com.github.itdachen.framework.rbac.IRbacPermissionService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: Rbac 鉴权模块
 * Created by 王大宸 on 2022-12-13 11:23
 * Created with IntelliJ IDEA.
 */
@Configuration
public class RbacPermissionBean {
    private final IPermissionCacheService permissionCacheService;

    public RbacPermissionBean(IPermissionCacheService permissionCacheService) {
        this.permissionCacheService = permissionCacheService;
    }

    @Bean
    @ConditionalOnMissingBean(IRbacPermissionService.class)
    public IRbacPermissionService rbacPermissionService() {
        return new RbacPermissionServiceImpl(permissionCacheService);
    }

}
