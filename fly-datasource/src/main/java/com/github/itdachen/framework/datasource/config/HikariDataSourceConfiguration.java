package com.github.itdachen.framework.datasource.config;

import com.github.itdachen.framework.datasource.IDataSourceDecrypt;
import com.github.itdachen.framework.datasource.factory.DataSourceDecryptFactory;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    private IDataSourceDecrypt dataSourceDecrypt;


    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;


    @Override
    public void afterPropertiesSet() throws Exception {
        if (super.getPassword() == null) {
            final String str = dataSourceDecrypt.decrypt(password);
            this.setPassword(str);
        }
        if (super.getUsername() == null) {
            final String str = dataSourceDecrypt.decrypt(username);
            this.setUsername(str);
        }
        if (super.getJdbcUrl() == null) {
            final String str = dataSourceDecrypt.decrypt(url);
            this.setJdbcUrl(str);
        }

    }

}
