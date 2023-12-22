package com.github.itdachen.boot.autoconfigure.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 单体项目 token 认证自动配置
 * Created by 王大宸 on 2023-07-22 13:31
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        JwtAutoconfigureProperties.class
})
public class JwtAutoConfiguration {
}
