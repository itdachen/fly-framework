package com.github.itdachen.framework.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.itdachen.framework.datasource.encoder.DataSourceEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
//    @Autowired
//    private DataSourceProperties basicProperties;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public DruidDataSourceConfiguration() {
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (super.getUsername() == null) {
            final String str = dataSourceEncoder.decrypt(username);
            super.setUsername(str);
        }

        if (super.getPassword() == null) {
            final String str = dataSourceEncoder.decrypt(password);
            super.setPassword(str);
        }

        if (super.getUrl() == null) {
            final String str = dataSourceEncoder.decrypt(url);
            super.setUrl(str);
        }

        if (super.getDriverClassName() == null) {
            final String str = dataSourceEncoder.decrypt(driverClassName);
            super.setDriverClassName(str);
        }
    }

}
