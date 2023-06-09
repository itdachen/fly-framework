package com.github.itdachen.framework.cloud.gateway.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Description:  事件推送 Aware: 动态更新路由网关 service
 * Created by 王大宸 on 2023/02/14 11:23
 * Created with IntelliJ IDEA.
 */
@Service
@SuppressWarnings("all")
public class DynamicRouteService implements ApplicationEventPublisherAware {
    private static final Logger logger = LoggerFactory.getLogger(DynamicRouteService.class);

    /**
     * 写路由定义
     */
    private final RouteDefinitionWriter routeDefinitionWriter;
    /**
     * 获取路由定义
     */
    private final RouteDefinitionLocator routeDefinitionLocator;
    /**
     * 事件发布
     */
    private ApplicationEventPublisher publisher;

    public DynamicRouteService(RouteDefinitionWriter routeDefinitionWriter,
                               RouteDefinitionLocator routeDefinitionLocator) {
        this.routeDefinitionWriter = routeDefinitionWriter;
        this.routeDefinitionLocator = routeDefinitionLocator;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        // 完成事件推送句柄的初始化
        this.publisher = applicationEventPublisher;
    }

    /***
     * 增加路由定义
     *
     * @author 王大宸
     * @date 2022/3/30 21:56
     * @param definition
     * @return java.lang.String
     */
    public String addRouteDefinition(RouteDefinition definition) {
        logger.info("gateway add route: [{}]", definition);
        // 保存路由配置并发布
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        // 发布事件通知给 Gateway, 同步新增的路由定义
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }

    /***
     * 更新路由
     *
     * @author 王大宸
     * @date 2022/3/30 21:56
     * @param definitions
     * @return java.lang.String
     */
    public String updateList(List<RouteDefinition> definitions) {
        logger.info("gateway update route: [{}]", definitions);
        // 先拿到当前 Gateway 中存储的路由定义
        List<RouteDefinition> routeDefinitionsExits =
                routeDefinitionLocator.getRouteDefinitions().buffer().blockFirst();
        if (!CollectionUtils.isEmpty(routeDefinitionsExits)) {
            // 清除掉之前所有的 "旧的" 路由定义
            routeDefinitionsExits.forEach(rd -> {
                logger.info("delete route definition: [{}]", rd);
                deleteById(rd.getId());
            });
        }
        // 把更新的路由定义同步到 gateway 中
        definitions.forEach(definition -> updateByRouteDefinition(definition));
        return "success";
    }

    /***
     * 根据路由 id 删除路由配置
     *
     * @author 王大宸
     * @date 2022/3/30 21:56
     * @param id
     * @return java.lang.String
     */
    private String deleteById(String id) {
        try {
            logger.info("gateway delete route id: [{}]", id);
            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
            // 发布事件通知给 gateway 更新路由定义
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "delete success";
        } catch (Exception ex) {
            logger.error("gateway delete route fail: [{}]", ex.getMessage(), ex);
            return "delete fail";
        }
    }

    /***
     * 更新路由 更新的实现策略比较简单: 删除 + 新增 = 更新
     *
     * @author 王大宸
     * @date 2022/3/30 21:56
     * @param definition
     * @return java.lang.String
     */
    private String updateByRouteDefinition(RouteDefinition definition) {
        try {
            logger.info("gateway update route: [{}]", definition);
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception ex) {
            return "update fail, not find route routeId: " + definition.getId();
        }
        try {
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        } catch (Exception ex) {
            return "update route fail";
        }
    }

}
