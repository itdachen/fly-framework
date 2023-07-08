package com.github.itdachen.framework.autoconfigure;

import com.github.itdachen.framework.autoconfigure.gateway.FlyGatewayIgnoreAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.gateway.FlyGatewayRoutesAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.jwt.FlyJwtAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.oss.FlyOssAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.oss.properties.FlyOssAliYunAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.oss.properties.FlyOssLocalAutoconfigureProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 自动配置模块, 所有配置都在这个模块集中管理
 * Created by 王大宸 on 2023-07-06 15:18
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        FlyOssAutoconfigureProperties.class,
        FlyOssLocalAutoconfigureProperties.class,
        FlyOssAliYunAutoconfigureProperties.class,
        FlyJwtAutoconfigureProperties.class,
        FlyGatewayRoutesAutoconfigureProperties.class,
        FlyGatewayIgnoreAutoconfigureProperties.class
})
public class FlyAutoConfigurationProperties {
}
