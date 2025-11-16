package com.github.itdachen.framework.boot.datasource;

import com.github.itdachen.framework.boot.autoconfigure.datasource.DataSourceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * DataSourceAutoConfiguration
 *
 * @author 剑鸣秋朔
 * @date 2023-12-27 21:10
 */
@Configuration
public class FlyDataSourceAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(FlyDataSourceAutoConfiguration.class);

    private final DataSource dataSource;
    private final DataSourceProperties dataSourceProperties;

    public FlyDataSourceAutoConfiguration(DataSource dataSource,
                                          DataSourceProperties dataSourceProperties) {
        this.dataSource = dataSource;
        this.dataSourceProperties = dataSourceProperties;
    }

    /***
     * 增加 Jdbc 模板支持
     *
     * @author 剑鸣秋朔
     * @date 2023/3/28 17:24
     * @return org.springframework.jdbc.core.JdbcTemplate
     */
    @Bean
    @Primary
    public JdbcTemplate getJdbcTemplate() {
        logger.info("data source adding jdbc support, encryption type is {}", dataSourceProperties.getEncoder());
        return new JdbcTemplate(dataSource);
    }

}
