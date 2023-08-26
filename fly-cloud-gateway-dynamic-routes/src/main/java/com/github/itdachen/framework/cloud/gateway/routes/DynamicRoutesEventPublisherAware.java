package com.github.itdachen.framework.cloud.gateway.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Description: 事件推送 Aware: 动态更新路由网关 service
 * Created by 王大宸 on 2023/02/14 11:23
 * Created with IntelliJ IDEA.
 */
@Service
@SuppressWarnings("all")
public class DynamicRoutesEventPublisherAware implements IRouteService, ApplicationEventPublisherAware {
    private static final Logger logger = LoggerFactory.getLogger(DynamicRoutesEventPublisherAware.class);

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    /**
     * 事件发布者
     */
    private ApplicationEventPublisher publisher;

    @Override
    public void update(RouteDefinition routeDefinition) {
        logger.info("更新路由配置项：{}", routeDefinition);
        // 删除原来的
        routeDefinitionWriter.delete(Mono.just(routeDefinition.getId()));

        // 重新添加
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void add(RouteDefinition routeDefinition) {
        logger.info("新增路由配置项：{}", routeDefinition);
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }




}
