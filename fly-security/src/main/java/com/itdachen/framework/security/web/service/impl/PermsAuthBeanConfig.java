package com.itdachen.framework.security.web.service.impl;

import com.itdachen.framework.security.web.service.IPermsAuthWebService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 获取菜单的默认实现
 * Created by 王大宸 on 2021-11-28 11:43
 * Created with IntelliJ IDEA.
 */
@Configuration
public class PermsAuthBeanConfig {

    /***
     * 提供默认提示,各子系统实现 PermsAuthService 接口中的方法即可
     *
     * @author 王大宸
     * @date 2021/11/28 11:44
     * @param
     * @return com.itdachen.security.core.authority.PermsAuthService
     */
    @Bean
    @ConditionalOnMissingBean(IPermsAuthWebService.class)
    public IPermsAuthWebService permsAuthService() {
        return new DefaultPermsAuthWebService();
    }

}
