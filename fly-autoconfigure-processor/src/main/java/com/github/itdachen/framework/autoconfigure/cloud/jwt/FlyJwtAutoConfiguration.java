package com.github.itdachen.framework.autoconfigure.cloud.jwt;

import com.github.itdachen.framework.autoconfigure.cloud.jwt.properties.FlyJwtAppClientProperties;
import com.github.itdachen.framework.autoconfigure.cloud.jwt.properties.FlyJwtTokenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023-07-08 23:16
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        FlyJwtTokenProperties.class,
        FlyJwtAppClientProperties.class
})
public class FlyJwtAutoConfiguration {
}
