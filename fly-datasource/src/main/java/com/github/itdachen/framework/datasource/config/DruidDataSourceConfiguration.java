package com.github.itdachen.framework.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.itdachen.framework.datasource.DataSourceEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Description: 阿里巴巴开源数据源自定义配置,连接地址可以加密
 * Created by 王大宸 on 2023/02/07 14:24
 * Created with IntelliJ IDEA.
 */
@Configuration
@SuppressWarnings("all")
@Primary
@ConditionalOnProperty(prefix = "spring.datasource", name = "type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
public class DruidDataSourceConfiguration extends DruidDataSource implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(DruidDataSourceConfiguration.class);

    @Autowired
    private DataSourceEncoder dataSourceEncoder;
    @Autowired
    private DataSourceProperties basicProperties;

    public DruidDataSourceConfiguration() {
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (super.getUsername() == null) {
            super.setUsername(dataSourceEncoder.decrypt(this.basicProperties.determineUsername()));
        }

        if (super.getPassword() == null) {
            super.setPassword(dataSourceEncoder.decrypt(this.basicProperties.determinePassword()));
        }

        if (super.getUrl() == null) {
            super.setUrl(dataSourceEncoder.decrypt(this.basicProperties.determineUrl()));
        }

        if (super.getDriverClassName() == null) {
            super.setDriverClassName(this.basicProperties.getDriverClassName());
        }
    }

}
