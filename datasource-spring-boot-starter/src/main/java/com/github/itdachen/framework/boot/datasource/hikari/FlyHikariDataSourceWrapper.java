package com.github.itdachen.framework.boot.datasource.hikari;

import com.github.itdachen.framework.boot.datasource.crypto.IDataSourceDecrypt;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * Description: hikari 数据源自定义配置,连接地址可以加密
 * Created by 王大宸 on 2023/01/28 15:10
 * Created with IntelliJ IDEA.
 */
//@Configuration
//@SuppressWarnings("all")
//@Primary
//@ConditionalOnProperty(prefix = "spring.datasource", name = "type", havingValue = "com.zaxxer.hikari.HikariDataSource")
public class FlyHikariDataSourceWrapper extends HikariDataSource implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(FlyHikariDataSourceWrapper.class);
    private final IDataSourceDecrypt dataSourceDecrypt;

    public FlyHikariDataSourceWrapper(IDataSourceDecrypt dataSourceDecrypt) {
        this.dataSourceDecrypt = dataSourceDecrypt;
    }


//    @Autowired
//    private DataSourceProperties basicProperties;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;


    @Override
    public void afterPropertiesSet() throws Exception {
        if (null == super.getPassword()) {
            final String str = dataSourceDecrypt.decrypt(password);
            this.setPassword(str);
        }
        if (null == super.getUsername()) {
            final String str = dataSourceDecrypt.decrypt(username);
            this.setUsername(str);
        }
        if (null == super.getJdbcUrl()) {
            final String str = dataSourceDecrypt.decrypt(url);
            this.setJdbcUrl(str);
        }

    }

}
