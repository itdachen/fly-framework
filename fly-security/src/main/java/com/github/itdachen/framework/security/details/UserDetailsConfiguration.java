package com.github.itdachen.framework.security.details;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * 刷新用户信息默认配置
 *
 * @author 王大宸
 * @date 2023-12-17 14:25
 */
@Configuration
public class UserDetailsConfiguration {

    private final SecurityContextRepository securityContextRepository;

    public UserDetailsConfiguration(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }


    @Bean
    @ConditionalOnMissingBean(IRefreshUserDetails.class)
    public IRefreshUserDetails refreshUserDetails() {
        return new RefreshUserDetailsHandler(securityContextRepository);
    }

}
