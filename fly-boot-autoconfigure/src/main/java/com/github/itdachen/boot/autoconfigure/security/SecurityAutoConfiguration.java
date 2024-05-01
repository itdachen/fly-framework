package com.github.itdachen.boot.autoconfigure.security;

import com.github.itdachen.boot.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.code.SecurityImageCodeProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.code.SecuritySmsCodeProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.rememberme.SecurityRememberMeProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.session.SecuritySessionProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.third.SecurityThirdProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: fly-security 模块配置文件
 * Created by 王大宸 on 2023-07-08 23:08
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        FlySecurityProperties.class,
        SecuritySessionProperties.class,
        SecurityRememberMeProperties.class,
        SecurityImageCodeProperties.class,
        SecuritySmsCodeProperties.class,
        SecurityThirdProperties.class
})
public class SecurityAutoConfiguration {
}
