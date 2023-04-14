package com.github.itdachen.framework.jjwt.config;

import com.github.itdachen.framework.jjwt.properties.JJwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023/04/12 22:18
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties(JJwtProperties.class)
public class JwtCoreConfiguration {
}
