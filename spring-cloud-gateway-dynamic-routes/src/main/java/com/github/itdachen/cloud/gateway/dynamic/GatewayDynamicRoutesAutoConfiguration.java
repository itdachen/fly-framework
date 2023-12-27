package com.github.itdachen.cloud.gateway.dynamic;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.itdachen.boot.autoconfigure.cloud.gateway.routes.GatewayRoutesProperties;
import com.github.itdachen.cloud.gateway.dynamic.publisher.IRouteEventPublisherService;
import com.github.itdachen.cloud.gateway.dynamic.routes.DynamicLoadNacosGatewayRoutes;
import com.github.itdachen.cloud.gateway.dynamic.publisher.DynamicRoutesEventPublisherAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * GatewayDynamicRoutesAutoConfiguration
 *
 * @author 王大宸
 * @date 2023-12-23 0:34
 */
@Configuration
@RefreshScope
public class GatewayDynamicRoutesAutoConfiguration {


    private final GatewayRoutesProperties routesProperties;

    public GatewayDynamicRoutesAutoConfiguration(GatewayRoutesProperties routesProperties) {
        this.routesProperties = routesProperties;
    }


    /***
    * 通过 nacos 下发动态路由配置, 监听 Nacos 中路由配置变更
    *
    * @author 王大宸
    * @date 2023/12/23 0:37
    * @return com.github.itdachen.cloud.gateway.dynamic.routes.DynamicLoadNacosGatewayRoutes
    */
    @Bean
    public DynamicLoadNacosGatewayRoutes dynamicLoadNacosGatewayRoutes(){
        return new DynamicLoadNacosGatewayRoutes();
    }

    /***
    * 事件推送 Aware: 动态更新路由网关 service
    *
    * @author 王大宸
    * @date 2023/12/23 0:38
    * @return com.github.itdachen.cloud.gateway.dynamic.publisher.DynamicRoutesEventPublisherAware
    */
    @Bean
    @ConditionalOnMissingBean(IRouteEventPublisherService.class)
    public IRouteEventPublisherService routeEventPublisherService() {
        return new DynamicRoutesEventPublisherAware();
    }



    /***
    * 配置信息
    *
    * @author 王大宸
    * @date 2023/12/23 0:40
    * @return com.alibaba.nacos.api.config.ConfigService
    */
    @Bean
    public ConfigService configService() throws NacosException {
        Properties properties = new Properties();

        /* 新版本, 需要添加 Nacos 账号密码 */
        properties.setProperty(PropertyKeyConst.USERNAME, routesProperties.getUsername());
        properties.setProperty(PropertyKeyConst.PASSWORD, routesProperties.getPassword());

        /*  配置 Nacos 地址和命名空间 */
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, routesProperties.getServerAddr());
        properties.setProperty(PropertyKeyConst.NAMESPACE, routesProperties.getNamespace());
        return NacosFactory.createConfigService(properties);
    }

}
