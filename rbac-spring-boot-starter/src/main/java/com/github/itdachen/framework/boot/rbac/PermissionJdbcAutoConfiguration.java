package com.github.itdachen.framework.boot.rbac;

import com.github.itdachen.framework.boot.rbac.jdbc.IPermissionJdbcService;
import com.github.itdachen.framework.boot.rbac.jdbc.IPermissionUserJdbcService;
import com.github.itdachen.framework.boot.rbac.jdbc.impl.DefaultPermissionJdbcService;
import com.github.itdachen.framework.boot.rbac.jdbc.impl.DefaultPermissionUserJdbcService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 剑鸣秋朔 on 2022-12-13 15:57
 * Created with IntelliJ IDEA.
 */
@Configuration
public class PermissionJdbcAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(IPermissionUserJdbcService.class)
    public IPermissionUserJdbcService permissionUserJdbcService() {
        return new DefaultPermissionUserJdbcService();
    }

    @Bean
    @ConditionalOnMissingBean(IPermissionJdbcService.class)
    public IPermissionJdbcService permissionJdbcService() {
        return new DefaultPermissionJdbcService();
    }


}
