package com.github.itdachen.framework.autoconfigure.gateway;

import com.github.itdachen.framework.autoconfigure.gateway.ignore.FlyGatewayIgnoreProperties;
import com.github.itdachen.framework.autoconfigure.gateway.routes.FlyGatewayRoutesProperties;
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
