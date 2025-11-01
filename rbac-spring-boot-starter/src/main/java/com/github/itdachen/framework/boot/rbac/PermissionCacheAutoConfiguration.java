package com.github.itdachen.framework.boot.rbac;

import com.github.itdachen.framework.boot.rbac.cache.IPermissionCacheService;
import com.github.itdachen.framework.boot.rbac.cache.impl.JdbcPermissionCacheService;
import com.github.itdachen.framework.boot.rbac.jdbc.IPermissionJdbcService;
import com.github.itdachen.framework.boot.rbac.jdbc.IPermissionUserJdbcService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 权限缓存
 * Created by 王大宸 on 2022-12-13 15:55
 * Created with IntelliJ IDEA.
 */
@Configuration
public class PermissionCacheAutoConfiguration {

    private final IPermissionJdbcService permissionJdbcService;
    private final IPermissionUserJdbcService permissionUserJdbcService;

    public PermissionCacheAutoConfiguration(IPermissionJdbcService permissionJdbcService,
                                            IPermissionUserJdbcService permissionUserJdbcService) {
        this.permissionJdbcService = permissionJdbcService;
        this.permissionUserJdbcService = permissionUserJdbcService;
    }

    @Bean
    @ConditionalOnMissingBean(IPermissionCacheService.class)
    public IPermissionCacheService permissionCacheService() {
        return new JdbcPermissionCacheService(permissionJdbcService, permissionUserJdbcService);
    }

}
