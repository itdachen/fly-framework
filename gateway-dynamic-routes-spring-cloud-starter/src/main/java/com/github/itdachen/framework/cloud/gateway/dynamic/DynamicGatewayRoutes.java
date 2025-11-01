package com.github.itdachen.framework.cloud.gateway.dynamic;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.itdachen.framework.boot.autoconfigure.cloud.gateway.routes.GatewayRoutesProperties;
import com.github.itdachen.framework.cloud.gateway.dynamic.publisher.IRouteEventPublisherService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

/**
 * 加载动态路由
 *
 * @author 王大宸
 * @date 2024-07-25 16:59
 */
@Configuration
@RefreshScope // 狗东西, 这里一定要加这个注解, 否则动态路由会卡住, 导致项目无法启动
public class DynamicGatewayRoutes implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(DynamicGatewayRoutes.class);

    private final GatewayRoutesProperties gatewayRoutesProperties;

    private final IRouteEventPublisherService routeEventPublisherService;

    /**
     * nacos 配置服务
     */
    private final ConfigService configService;

    /**
     * JSON 转换对象
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DynamicGatewayRoutes(GatewayRoutesProperties gatewayRoutesProperties,
                                IRouteEventPublisherService routeEventPublisherService,
                                ConfigService configService) {
        this.gatewayRoutesProperties = gatewayRoutesProperties;
        this.routeEventPublisherService = routeEventPublisherService;
        this.configService = configService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initGatewayRoutes();
    }

    public void initGatewayRoutes() {
        logger.info("开始网关动态路由初始化...");
        try {
            // getConfigAndSignListener()方法 发起长轮询和对dataId数据变更注册监听的操作
            // getConfig 只是发送普通的HTTP请求
            String initConfigInfo = configService.getConfigAndSignListener(
                    gatewayRoutesProperties.getDataId(),
                    gatewayRoutesProperties.getGroup(),
                    gatewayRoutesProperties.getTimeout(),
                    new Listener() {
                        @Override
                        public Executor getExecutor() {
                            return null;
                        }

                        @Override
                        public void receiveConfigInfo(String configInfo) {
                            if (StringUtils.isNotEmpty(configInfo)) {
                                logger.info("接收到网关路由更新配置：\r\n{}", configInfo);
                                List<RouteDefinition> routeDefinitions = null;
                                try {
                                    routeDefinitions = objectMapper.readValue(
                                            configInfo,
                                            new TypeReference<List<RouteDefinition>>() {
                                            }
                                    );
                                } catch (JsonProcessingException e) {
                                    logger.error("解析路由配置出错，" + e.getMessage(), e);
                                }
                                for (RouteDefinition definition : Objects.requireNonNull(routeDefinitions)) {
                                    routeEventPublisherService.update(definition);
                                }
                            } else {
                                logger.warn("当前网关无动态路由相关配置");
                            }
                        }
                    });
            logger.info("获取网关当前动态路由配置:\r\n{}", initConfigInfo);
            if (StringUtils.isNotEmpty(initConfigInfo)) {
                List<RouteDefinition> routeDefinitions = objectMapper.readValue(initConfigInfo,
                        new TypeReference<List<RouteDefinition>>() {
                        }
                );
                for (RouteDefinition definition : routeDefinitions) {
                    routeEventPublisherService.add(definition);
                }
            } else {
                logger.warn("当前网关无动态路由相关配置");
            }
            logger.info("结束网关动态路由初始化...");
        } catch (Exception e) {
            logger.error("初始化网关路由时发生错误", e);
        }
    }


}
