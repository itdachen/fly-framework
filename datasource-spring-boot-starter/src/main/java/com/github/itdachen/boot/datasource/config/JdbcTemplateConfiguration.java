package com.github.itdachen.boot.datasource.config;

import com.github.itdachen.boot.autoconfigure.datasource.DataSourceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Description: 添加 jdbc 支持
 * Created by 王大宸 on 2023/03/08 15:43
 * Created with IntelliJ IDEA.
 */
@Configuration
public class JdbcTemplateConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(JdbcTemplateConfiguration.class);

    private final DataSource dataSource;
    private final DataSourceProperties dataSourceProperties;

    public JdbcTemplateConfiguration(DataSource dataSource,
                                     DataSourceProperties dataSourceProperties) {
        this.dataSource = dataSource;
        this.dataSourceProperties = dataSourceProperties;
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
        logger.info("Data source adding jdbc support, Encryption type is {}", dataSourceProperties.getEncoder());
        return new JdbcTemplate(dataSource);
    }

}
