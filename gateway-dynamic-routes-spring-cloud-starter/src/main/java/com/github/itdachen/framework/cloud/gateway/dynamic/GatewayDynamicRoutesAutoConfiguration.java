package com.github.itdachen.framework.cloud.gateway.dynamic;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.itdachen.framework.boot.autoconfigure.cloud.gateway.routes.GatewayRoutesProperties;
import com.github.itdachen.framework.cloud.gateway.dynamic.publisher.DynamicRoutesEventPublisherAware;
import com.github.itdachen.framework.cloud.gateway.dynamic.publisher.IRouteEventPublisherService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * GatewayDynamicRoutesAutoConfiguration
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 0:34
 */
@Configuration
@RefreshScope
public class GatewayDynamicRoutesAutoConfiguration {

    private final GatewayRoutesProperties gatewayRoutesProperties;
    private final RouteDefinitionWriter routeDefinitionWriter;

    public GatewayDynamicRoutesAutoConfiguration(GatewayRoutesProperties gatewayRoutesProperties,
                                                 RouteDefinitionWriter routeDefinitionWriter) {
        this.gatewayRoutesProperties = gatewayRoutesProperties;
        this.routeDefinitionWriter = routeDefinitionWriter;
    }




    @Bean
    @ConditionalOnMissingBean(IRouteEventPublisherService.class)
    public IRouteEventPublisherService dynamicRoutesEventPublisherAware() {
        return new DynamicRoutesEventPublisherAware(routeDefinitionWriter);
    }


    /***
     * 配置信息
     *
     * @author 剑鸣秋朔
     * @date 2024/7/25 17:07
     * @return com.alibaba.nacos.api.config.ConfigService
     */
    @Bean
    public ConfigService configService() throws NacosException {
        Properties properties = new Properties();
        /*  配置 Nacos 地址和命名空间 */
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, gatewayRoutesProperties.getServerAddr());
        properties.setProperty(PropertyKeyConst.NAMESPACE, gatewayRoutesProperties.getNamespace());

        /* 新版本, 需要添加 Nacos 账号密码 */
        properties.setProperty(PropertyKeyConst.USERNAME, gatewayRoutesProperties.getUsername());
        properties.setProperty(PropertyKeyConst.PASSWORD, gatewayRoutesProperties.getPassword());

        return NacosFactory.createConfigService(properties);
    }

}
