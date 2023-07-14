package com.github.itdachen.framework.autoconfigure.cloud.gateway;

import com.github.itdachen.framework.autoconfigure.cloud.gateway.routes.FlyGatewayRoutesProperties;
import com.github.itdachen.framework.autoconfigure.cloud.gateway.ignore.FlyGatewayIgnoreProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023-07-08 23:17
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        FlyGatewayRoutesProperties.class,
        FlyGatewayIgnoreProperties.class
})
public class FlyGatewayAutoConfiguration {
}
