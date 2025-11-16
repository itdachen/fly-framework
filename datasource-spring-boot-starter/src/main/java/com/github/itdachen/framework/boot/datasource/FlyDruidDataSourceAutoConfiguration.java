package com.github.itdachen.framework.boot.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot3.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.spring.boot3.autoconfigure.stat.DruidFilterConfiguration;
import com.alibaba.druid.spring.boot3.autoconfigure.stat.DruidSpringAopConfiguration;
import com.alibaba.druid.spring.boot3.autoconfigure.stat.DruidStatViewServletConfiguration;
import com.alibaba.druid.spring.boot3.autoconfigure.stat.DruidWebStatFilterConfiguration;
import com.github.itdachen.framework.boot.datasource.crypto.IDataSourceDecrypt;
import com.github.itdachen.framework.boot.datasource.druid.FlyDruidDataSourceWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * alibaba Druid 自定义连接, 添加加密
 * 排除默认的 DruidDataSourceAutoConfigure 配置启动
 *
 * @author 剑鸣秋朔
 * @date 2024-01-10 21:35
 */
@Configuration
@ConditionalOnClass({DruidDataSource.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({DruidStatProperties.class, DataSourceProperties.class})
@Import({DruidSpringAopConfiguration.class, DruidStatViewServletConfiguration.class, DruidWebStatFilterConfiguration.class, DruidFilterConfiguration.class})
@ConditionalOnProperty(prefix = "spring.datasource", name = "type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
//@EnableAutoConfiguration(exclude = {DruidDataSourceAutoConfigure.class})
public class FlyDruidDataSourceAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlyDruidDataSourceAutoConfiguration.class);

    private final IDataSourceDecrypt dataSourceDecrypt;
    private final DataSourceProperties basicProperties;

    public FlyDruidDataSourceAutoConfiguration(IDataSourceDecrypt dataSourceDecrypt,
                                               DataSourceProperties basicProperties) {
        this.dataSourceDecrypt = dataSourceDecrypt;
        this.basicProperties = basicProperties;
    }

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        LOGGER.info("Init Fly DruidDataSource");
        return new FlyDruidDataSourceWrapper(dataSourceDecrypt);
    }


}
