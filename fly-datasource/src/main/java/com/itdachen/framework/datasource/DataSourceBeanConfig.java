package com.itdachen.framework.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Description:
 * Created by 王大宸 on 2023/03/08 15:43
 * Created with IntelliJ IDEA.
 */
@Configuration
public class DataSourceBeanConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceBeanConfig.class);

    private final DataSource dataSource;

    public DataSourceBeanConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /***
     * 功能说明：增加Jdbc模板支持
     *
     * @author 王大宸
     * @date 2020/10/11 15:32
     * @param
     * @return org.springframework.jdbc.core.JdbcTemplate
     */
    @Bean
    @Primary
    public JdbcTemplate getJdbcTemplate() {
        logger.error("数据库添加Jdbc模板支持...");
        return new JdbcTemplate(dataSource);
    }

}
