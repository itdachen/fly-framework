package com.github.itdachen.cloud.gateway.dynamic.routes;

import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * Description: 新增和修改理由接口
 * Created by 王大宸 on 2023-08-26 21:30
 * Created with IntelliJ IDEA.
 */
public interface IRouteService {


    /***
     * 更新路由配置
     *
     * @author 王大宸
     * @date 2023/8/26 21:30
     * @param routeDefinition routeDefinition
     * @return void
     */
    void update(RouteDefinition routeDefinition);

    /***
     * 添加路由配置
     *
     * @author 王大宸
     * @date 2023/8/26 21:33
     * @param routeDefinition routeDefinition
     * @return void
     */
    void add(RouteDefinition routeDefinition);


}
