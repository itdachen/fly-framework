package com.github.itdachen.framework.cloud.gateway.routes;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.itdachen.framework.autoconfigure.cloud.gateway.routes.FlyGatewayRoutesProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Description: 配置信息
 * Created by 王大宸 on 2023-08-26 21:33
 * Created with IntelliJ IDEA.
 */
@Configuration
public class GatewayRoutesConfigService {

    private final FlyGatewayRoutesProperties routesProperties;

    public GatewayRoutesConfigService(FlyGatewayRoutesProperties routesProperties) {
        this.routesProperties = routesProperties;
    }

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
