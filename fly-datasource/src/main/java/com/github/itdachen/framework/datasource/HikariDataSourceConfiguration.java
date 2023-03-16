package com.github.itdachen.framework.datasource;

import com.github.itdachen.framework.datasource.encoder.DataSourceEncoder;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Description: hikari 数据源自定义配置,连接地址可以加密
 * Created by 王大宸 on 2023/01/28 15:10
 * Created with IntelliJ IDEA.
 */
@Configuration
@SuppressWarnings("all")
@Primary
@ConditionalOnProperty(prefix = "spring.datasource", name = "type", havingValue = "com.zaxxer.hikari.HikariDataSource")
public class HikariDataSourceConfiguration extends HikariDataSource implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(HikariDataSourceConfiguration.class);

    @Autowired
    private DataSourceEncoder dataSourceEncoder;
    @Autowired
    private DataSourceProperties basicProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setPassword(dataSourceEncoder.decrypt(basicProperties.getPassword()));
        this.setUsername(dataSourceEncoder.decrypt(basicProperties.getUsername()));
        this.setJdbcUrl(dataSourceEncoder.decrypt(basicProperties.getUrl()));
    }

}
