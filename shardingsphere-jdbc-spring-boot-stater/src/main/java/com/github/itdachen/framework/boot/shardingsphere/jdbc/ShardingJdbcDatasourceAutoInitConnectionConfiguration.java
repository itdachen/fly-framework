package com.github.itdachen.framework.boot.shardingsphere.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 后续考虑如何将这个配置类做成一个参数控制
 *
 * @author 剑鸣秋朔
 * @date 2025/11/1 22:48
 */
@Configuration
public class ShardingJdbcDatasourceAutoInitConnectionConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ShardingJdbcDatasourceAutoInitConnectionConfiguration.class);

    @Bean
    public ApplicationRunner runner(DataSource dataSource) {
        return args -> {
            logger.info("sharding jdbc datasource connection: {}", dataSource);
            //手动触发下连接池的连接创建
            Connection connection = dataSource.getConnection();
        };
    }


}
