package com.github.itdachen.framework.boot.autoconfigure.cloud.gateway;

import com.github.itdachen.framework.boot.autoconfigure.cloud.gateway.ignore.GatewayIgnoreProperties;
import com.github.itdachen.framework.boot.autoconfigure.cloud.gateway.routes.GatewayRoutesProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023-07-08 23:17
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        GatewayRoutesProperties.class,
        GatewayIgnoreProperties.class
})
public class GatewayAutoConfiguration {
}
