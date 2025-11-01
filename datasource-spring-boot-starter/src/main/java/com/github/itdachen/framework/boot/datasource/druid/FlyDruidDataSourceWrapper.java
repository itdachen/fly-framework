package com.github.itdachen.framework.boot.datasource.druid;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.itdachen.framework.boot.datasource.crypto.IDataSourceDecrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import java.util.List;

/**
 * Description: 阿里巴巴开源数据源自定义配置,连接地址可以加密
 * Created by 王大宸 on 2023/02/07 14:24
 * Created with IntelliJ IDEA.
 */
//@ConfigurationProperties("spring.datasource.druid")
public class FlyDruidDataSourceWrapper extends DruidDataSource implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(FlyDruidDataSourceWrapper.class);


//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.datasource.username}")
//    private String username;
//    @Value("${spring.datasource.password}")
//    private String password;


    private final IDataSourceDecrypt dataSourceDecrypt;
  //  private final DataSourceProperties basicProperties;

    @Autowired
    private DataSourceProperties basicProperties;

    public FlyDruidDataSourceWrapper(IDataSourceDecrypt dataSourceDecrypt
                                     // DataSourceProperties basicProperties
    ) {
        this.dataSourceDecrypt = dataSourceDecrypt;
       // this.basicProperties = basicProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (null == super.getUsername()) {
            final String str = dataSourceDecrypt.decrypt(basicProperties.getUsername());
            super.setUsername(str);
        }
        if (null == super.getPassword()) {
            final String str = dataSourceDecrypt.decrypt(basicProperties.getPassword());
            super.setPassword(str);
        }
        if (null == super.getUrl()) {
            final String str = dataSourceDecrypt.decrypt(basicProperties.getUrl());
            super.setUrl(str);
        }
        if (null == super.getDriverClassName()) {
            super.setDriverClassName(basicProperties.getDriverClassName());
        }


//        if (super.getUsername() == null) {
//            super.setUsername(this.basicProperties.determineUsername());
//        }
//
//        if (super.getPassword() == null) {
//            super.setPassword(this.basicProperties.determinePassword());
//        }
//
//        if (super.getUrl() == null) {
//            super.setUrl(this.basicProperties.determineUrl());
//        }
//
//        if (super.getDriverClassName() == null) {
//            super.setDriverClassName(this.basicProperties.getDriverClassName());
//        }

    }

    @Autowired(
            required = false
    )
    public void autoAddFilters(List<Filter> filters) {
        super.filters.addAll(filters);
    }

    public void setMaxEvictableIdleTimeMillis(long maxEvictableIdleTimeMillis) {
        try {
            super.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        } catch (IllegalArgumentException var4) {
            super.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
        }

    }

}
