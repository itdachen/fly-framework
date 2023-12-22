package com.github.itdachen.boot.autoconfigure.cloud.jwt;

import com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudAppClientProperties;
import com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudTokenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023-07-08 23:16
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        CloudTokenProperties.class,
        CloudAppClientProperties.class
})
public class CloudJwtAutoConfiguration {
}
