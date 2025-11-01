package com.github.itdachen.framework.cloud.gateway.dynamic.publisher;

import org.springframework.cloud.gateway.route.RouteDefinition;

/***
* 新增和修改理由接口
*
* @author 王大宸
* @date 2024/7/25 16:57
*/
public interface IRouteEventPublisherService {


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
