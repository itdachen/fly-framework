package com.github.itdachen.framework.cloud.gateway.routes;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.github.itdachen.framework.autoconfigure.properties.FlyAutoconfigureProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Description: 通过 nacos 下发动态路由配置, 监听 Nacos 中路由配置变更
 * Created by 王大宸 on 2023/02/14 11:22
 * Created with IntelliJ IDEA.
 */
@Component
public class NacosDynamicRouteService {
    private static final Logger logger = LoggerFactory.getLogger(NacosDynamicRouteService.class);

    /**
     * nacos 配置服务
     */
    private ConfigService configService;

    private final DynamicRouteService dynamicRouteService;
    private final FlyAutoconfigureProperties autoconfigureProperties;

    public NacosDynamicRouteService(DynamicRouteService dynamicRouteService,
                                    FlyAutoconfigureProperties autoconfigureProperties) {
        this.dynamicRouteService = dynamicRouteService;
        this.autoconfigureProperties = autoconfigureProperties;
    }

    /***
     * Bean 在容器中构造完成之后会执行 init 方法
     *
     * @author 王大宸
     * @date 2022/3/30 21:52
     * @param
     * @return void
     */
    @PostConstruct // 在容器构造完成之后就执行
    public void init() {
        logger.info("gateway routes init....");
        try {
            // 初始化 Nacos 配置客户端
            configService = initConfigService();
            if (null == configService) {
                logger.error("init config service fail");
                return;
            }
            // 通过 Nacos Config 并指定路由配置路径去获取路由配置
            String configInfo = configService.getConfig(
                    autoconfigureProperties.getGateway().getRoutes().getDataId(),
                    autoconfigureProperties.getGateway().getRoutes().getGroup(),
                    autoconfigureProperties.getGateway().getRoutes().getTimeout()
            );
            logger.info("get current gateway config: [{}]", configInfo);
            List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
            if (CollectionUtils.isNotEmpty(definitionList)) {
                for (RouteDefinition definition : definitionList) {
                    logger.info("init gateway config: [{}]", definition.toString());
                    dynamicRouteService.addRouteDefinition(definition);
                }
            }
        } catch (Exception ex) {
            logger.error("gateway route init has some error: [{}]", ex.getMessage(), ex);
        }
        // 设置监听器
        dynamicRouteByNacosListener(
                autoconfigureProperties.getGateway().getRoutes().getDataId(),
                autoconfigureProperties.getGateway().getRoutes().getGroup()
        );
    }

    /***
     * 初始化 Nacos Config
     *
     * @author 王大宸
     * @date 2022/3/30 21:52
     * @param
     * @return com.alibaba.nacos.api.config.ConfigService
     */
    private ConfigService initConfigService() {
        try {
            Properties properties = new Properties();
            properties.setProperty("serverAddr", autoconfigureProperties.getGateway().getRoutes().getServerAddr());
            properties.setProperty("namespace", autoconfigureProperties.getGateway().getRoutes().getNamespace());
            return configService = NacosFactory.createConfigService(properties);
        } catch (Exception ex) {
            logger.error("init gateway nacos config error: [{}]", ex.getMessage(), ex);
            return null;
        }
    }

    /***
     * 监听 Nacos 下发的动态路由配置
     *
     * @author 王大宸
     * @date 2022/3/30 21:52
     * @param dataId
     * @param group
     * @return void
     */
    private void dynamicRouteByNacosListener(String dataId, String group) {
        try {
            // 给 Nacos Config 客户端增加一个监听器
            configService.addListener(dataId, group, new Listener() {
                /***
                 * 自己提供线程池执行操作
                 *
                 * @author 王大宸
                 * @date 2022/3/30 21:52
                 * @param
                 * @return java.util.concurrent.Executor
                 */
                @Override
                public Executor getExecutor() {
                    return null;
                }

                /***
                 * 监听器收到配置更新
                 *
                 * @author 王大宸
                 * @date 2022/3/30 21:52
                 * @param configInfo  Nacos 中最新的配置定义
                 * @return void
                 */
                @Override
                public void receiveConfigInfo(String configInfo) {
                    logger.info("start to update config: [{}]", configInfo);
                    List<RouteDefinition> definitionList =
                            JSON.parseArray(configInfo, RouteDefinition.class);
                    logger.info("update route: [{}]", definitionList.toString());
                    dynamicRouteService.updateList(definitionList);
                }
            });
        } catch (NacosException ex) {
            logger.error("dynamic update gateway config error: [{}]", ex.getMessage(), ex);
        }
    }


}
