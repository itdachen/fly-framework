package com.github.itdachen.framework.security.matchers;

import com.github.itdachen.framework.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.autoconfigure.security.properties.session.FlySecuritySessionProperties;
import com.github.itdachen.framework.security.matchers.byo.DefaultPassMatchers;
import com.github.itdachen.framework.security.matchers.byo.DefaultSecurityPassMatchers;
import com.github.itdachen.framework.security.matchers.pass.IPassMatchers;
import com.github.itdachen.framework.security.matchers.pass.ISecurityPassMatchers;
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

    private final FlySecurityProperties securityProperties;
    private final FlySecuritySessionProperties sessionProperties;
    private final Environment env;

    public BrowserPassMatchersBeanConfig(FlySecurityProperties securityProperties,
                                         FlySecuritySessionProperties sessionProperties,
                                         Environment env) {
        this.securityProperties = securityProperties;
        this.sessionProperties = sessionProperties;
        this.env = env;
    }

    @Bean
    @ConditionalOnMissingBean(IPassMatchers.class)
    public IPassMatchers defaultPassMatchers() {
        return new DefaultPassMatchers(securityProperties, sessionProperties);
    }

    @Bean
    @ConditionalOnMissingBean(ISecurityPassMatchers.class)
    public ISecurityPassMatchers defaultSecurityPassMatchers() {
        return new DefaultSecurityPassMatchers(securityProperties, sessionProperties, env);
    }

}
