package com.github.itdachen.framework.cloud.gateway.routes;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.itdachen.framework.autoconfigure.cloud.gateway.routes.FlyGatewayRoutesProperties;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

/**
 * Description:  通过 nacos 下发动态路由配置, 监听 Nacos 中路由配置变更
 * Created by 王大宸 on 2023-08-26 21:37
 * Created with IntelliJ IDEA.
 */
@Component
@RefreshScope
public class DynamicLoadNacosGatewayRoutes {
    private static final Logger logger = LoggerFactory.getLogger(DynamicLoadNacosGatewayRoutes.class);

    @Autowired
    private FlyGatewayRoutesProperties routesProperties;

    @Autowired
    private IRouteService routeService;

    /**
     * nacos 配置服务
     */
    @Autowired
    private ConfigService configService;

    /**
     * JSON 转换对象
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        logger.info("开始网关动态路由初始化...");
        try {
            // getConfigAndSignListener()方法 发起长轮询和对dataId数据变更注册监听的操作
            // getConfig 只是发送普通的HTTP请求
            String initConfigInfo = configService.getConfigAndSignListener(
                    routesProperties.getDataId(),
                    routesProperties.getGroup(),
                    routesProperties.getTimeout(),
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
                                    routeService.update(definition);
                                }
                            } else {
                                logger.warn("当前网关无动态路由相关配置");
                            }
                        }
                    });
            logger.info("获取网关当前动态路由配置:\r\n{}", initConfigInfo);
            if (StringUtils.isNotEmpty(initConfigInfo)) {
                List<RouteDefinition> routeDefinitions
                        = objectMapper.readValue(initConfigInfo,
                        new TypeReference<List<RouteDefinition>>() {
                        }
                );
                for (RouteDefinition definition : routeDefinitions) {
                    routeService.add(definition);
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
