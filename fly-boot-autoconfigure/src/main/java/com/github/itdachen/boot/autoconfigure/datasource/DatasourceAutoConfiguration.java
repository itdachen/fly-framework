package com.github.itdachen.boot.autoconfigure.datasource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 数据库连接加密配置
 * Created by 王大宸 on 2023-08-15 9:18
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        DataSourceProperties.class,
        DynamicDataSourceProperties.class
})
public class DatasourceAutoConfiguration {
}
