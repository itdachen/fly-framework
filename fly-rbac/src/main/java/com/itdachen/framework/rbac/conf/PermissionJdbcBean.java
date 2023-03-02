package com.itdachen.framework.rbac.conf;

import com.itdachen.framework.rbac.jdbc.IPermissionJdbcService;
import com.itdachen.framework.rbac.jdbc.IPermissionUserJdbcService;
import com.itdachen.framework.rbac.jdbc.impl.DefaultPermissionJdbcService;
import com.itdachen.framework.rbac.jdbc.impl.DefaultPermissionUserJdbcService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2022-12-13 15:57
 * Created with IntelliJ IDEA.
 */
@Configuration
public class PermissionJdbcBean {

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
