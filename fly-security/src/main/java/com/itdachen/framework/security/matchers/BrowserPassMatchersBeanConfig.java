package com.itdachen.framework.security.matchers;

import com.itdachen.framework.security.matchers.byo.DefaultPassMatchers;
import com.itdachen.framework.security.matchers.byo.DefaultSecurityPassMatchers;
import com.itdachen.framework.security.matchers.pass.IPassMatchers;
import com.itdachen.framework.security.matchers.pass.ISecurityPassMatchers;
import com.itdachen.framework.security.properties.SecurityBrowserProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Description: 添加默认的不拦截接口, 从配置文件中读取出来
 * Created by 王大宸 on 2022-09-23 15:03
 * Created with IntelliJ IDEA.
 */
@Configuration
public class BrowserPassMatchersBeanConfig {

    private final SecurityBrowserProperties securityProperties;
    private final Environment env;

    public BrowserPassMatchersBeanConfig(SecurityBrowserProperties securityProperties,
                                         Environment env) {
        this.securityProperties = securityProperties;
        this.env = env;
    }

    @Bean
    @ConditionalOnMissingBean(IPassMatchers.class)
    public IPassMatchers defaultPassMatchers() {
        return new DefaultPassMatchers(securityProperties);
    }

    @Bean
    @ConditionalOnMissingBean(ISecurityPassMatchers.class)
    public ISecurityPassMatchers defaultSecurityPassMatchers() {
        return new DefaultSecurityPassMatchers(securityProperties, env);
    }

}
