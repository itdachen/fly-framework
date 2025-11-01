package com.github.itdachen.framework.boot.datasource;

import com.github.itdachen.framework.boot.datasource.dynamic.aspect.DynamicDataSourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用时, 需要排除默认数据源加载方式: @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
 *
 * @author 王大宸
 * @date 2023-12-30 17:38
 */
@Configuration
public class DynamicDateSourceAutoConfiguration {

    /***
     * aop
     *
     * @author 王大宸
     * @date 2023/12/30 19:08
     * @return com.github.itdachen.boot.datasource.dynamic.aspect.DynamicDataSourceAspect
     */
    @Bean
    public DynamicDataSourceAspect dynamicDataSourceAspect() {
        return new DynamicDataSourceAspect();
    }


}
