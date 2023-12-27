package com.github.itdachen.boot.datasource;

import com.github.itdachen.boot.autoconfigure.datasource.DataSourceProperties;
import com.github.itdachen.boot.datasource.crypto.IDataSourceDecrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * DataSourceAutoConfiguration
 *
 * @author 王大宸
 * @date 2023-12-27 21:10
 */
@Configuration
public class DataSourceAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceAutoConfiguration.class);


    private final DataSource dataSource;
    private final DataSourceProperties dataSourceProperties;
    private final IDataSourceDecrypt dataSourceDecrypt;

    public DataSourceAutoConfiguration(DataSource dataSource,
                                       DataSourceProperties dataSourceProperties,
                                       IDataSourceDecrypt dataSourceDecrypt) {
        this.dataSource = dataSource;
        this.dataSourceProperties = dataSourceProperties;
        this.dataSourceDecrypt = dataSourceDecrypt;
    }


    @Bean
    @SuppressWarnings("all")
    @Primary
    @ConditionalOnProperty(prefix = "spring.datasource", name = "type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
    public DruidDataSourceConfiguration druidDataSourceConfiguration() {
        return new DruidDataSourceConfiguration(dataSourceDecrypt);
    }

    @Bean
    @SuppressWarnings("all")
    @Primary
    @ConditionalOnProperty(prefix = "spring.datasource", name = "type", havingValue = "com.zaxxer.hikari.HikariDataSource")
    public HikariDataSourceConfiguration hikariDataSourceConfiguration(){
        return new HikariDataSourceConfiguration(dataSourceDecrypt);
    }

    /***
     * 增加 Jdbc 模板支持
     *
     * @author 王大宸
     * @date 2023/3/28 17:24
     * @return org.springframework.jdbc.core.JdbcTemplate
     */
    @Bean
    @Primary
    public JdbcTemplate getJdbcTemplate() {
        logger.info("Data source adding jdbc support, encryption type is {}", dataSourceProperties.getEncoder());
        return new JdbcTemplate(dataSource);
    }

}
