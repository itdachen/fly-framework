package com.github.itdachen.framework.autoconfigure.security;

import com.github.itdachen.framework.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.autoconfigure.security.properties.code.FlySecurityImageCodeProperties;
import com.github.itdachen.framework.autoconfigure.security.properties.code.FlySecuritySmsCodeProperties;
import com.github.itdachen.framework.autoconfigure.security.properties.rememberme.FlySecurityRememberMeProperties;
import com.github.itdachen.framework.autoconfigure.security.properties.session.FlySecuritySessionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023-07-08 23:08
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        FlySecurityProperties.class,
        FlySecuritySessionProperties.class,
        FlySecurityRememberMeProperties.class,
        FlySecurityImageCodeProperties.class,
        FlySecuritySmsCodeProperties.class
})
public class FlySecurityAutoConfiguration {
}
