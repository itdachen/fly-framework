package com.github.itdachen.framework.cloud.gateway.dynamic.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import reactor.core.publisher.Mono;

/**
 * 动态路由增删改方法
 *
 * @author 王大宸
 * @date 2024-07-25 15:54
 */
public class DynamicRoutesEventPublisherAware implements IRouteEventPublisherService, ApplicationEventPublisherAware {
    private static final Logger logger = LoggerFactory.getLogger(DynamicRoutesEventPublisherAware.class);

    private final RouteDefinitionWriter routeDefinitionWriter;

    /**
     * 事件发布者
     */
    private ApplicationEventPublisher publisher;

    public DynamicRoutesEventPublisherAware(RouteDefinitionWriter routeDefinitionWriter) {
        this.routeDefinitionWriter = routeDefinitionWriter;
    }

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
