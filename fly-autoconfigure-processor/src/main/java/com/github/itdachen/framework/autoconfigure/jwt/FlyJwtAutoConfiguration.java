package com.github.itdachen.framework.autoconfigure.jwt;

import com.github.itdachen.framework.autoconfigure.jwt.properties.FlyJwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023-07-08 23:16
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({FlyJwtProperties.class})
public class FlyJwtAutoConfiguration {
}
