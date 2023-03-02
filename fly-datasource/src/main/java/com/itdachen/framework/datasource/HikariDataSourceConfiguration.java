package com.itdachen.framework.datasource;

import com.itdachen.framework.datasource.encoder.DataSourceEncoder;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Description: hikari 数据源自定义配置,连接地址可以加密
 * Created by 王大宸 on 2023/01/28 15:10
 * Created with IntelliJ IDEA.
 */
@Configuration
@SuppressWarnings("all")
@ConditionalOnProperty(prefix = "spring.datasource", name = "type", havingValue = "com.zaxxer.hikari.HikariDataSource")
public class HikariDataSourceConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(HikariDataSourceConfiguration.class);

    @Autowired
    private DataSourceEncoder dataSourceEncoder;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.hikari.pool-name}")
    private String poolName = "itDaChenAdminHikariPC";

    /* 最大连接数，小于等于0会被重置为默认值10；大于零小于1会被重置为minimum-idle的值, 合理算法: cpu核心数*2+磁盘数 */
    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize = 10;

    /* 最小空闲连接，默认值10，小于0或大于maximum-pool-size，都会重置为maximum-pool-size */
    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minimumIdle = 10;

    /* 空闲连接超时时间，默认值600000（10分钟），大于等于max-lifetime且max-lifetime>0，会被重置为0；不等于0且小于10秒，会被重置为10秒。 */
    @Value("${spring.datasource.hikari.idle-timeout}")
    private Integer idleTimeout = 600000;

    /* 连接超时时间：毫秒，小于250毫秒，否则被重置为默认值30秒 */
    @Value("${spring.datasource.hikari.connection-timeout}")
    private int connectionTimeout;

    /* 用于测试连接是否可用的查询语句 */
    @Value("${spring.datasource.hikari.connection-test-query}")
    private String connectionTestQuery;

    /* 连接最大存活时间，不等于0且小于30秒，会被重置为默认值30分钟.设置应该比mysql设置的超时时间短 */
    @Value("${spring.datasource.hikari.max-lifetime}")
    private int maxLifetime;

    /* 自动提交从池中返回的连接 */
    @Value("${spring.datasource.hikari.auto-commit}")
    private Boolean autoCommit = true;

    /* 连接将被测试活动的最大时间量,如果小于250毫秒，则会被重置回5秒 */
    @Value("${spring.datasource.hikari.validation-timeout}")
    private Integer validationTimeout = 5000;

    /* 记录消息之前连接可能离开池的时间量，表示可能的连接泄漏(500000)
     * 如果大于0且不是单元测试，则进一步判断：(leakDetectionThreshold < SECONDS.toMillis(2) or (leakDetectionThreshold > maxLifetime && maxLifetime > 0)，会被重置为0 . 即如果要生效则必须>0，而且不能小于2秒，而且当maxLifetime > 0时不能大于maxLifetime
     */
    @Value("${spring.datasource.hikari.leak-detection-threshold}")
    private Integer leakDetectionThreshold = 0;

    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    @Bean(name = "dataSource", destroyMethod = "close")
    @ConditionalOnProperty(prefix = "config.datasource", name = "encoder", havingValue = "AES")
    public DataSource dataSource() {
        logger.info("custom init encoder datasource url ...");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(dataSourceEncoder.decrypt(jdbcUrl));
        dataSource.setUsername(dataSourceEncoder.decrypt(username));
        dataSource.setPassword(dataSourceEncoder.decrypt(password));

        dataSource.setPoolName(poolName);
        dataSource.setConnectionTestQuery(connectionTestQuery);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setMinimumIdle(minimumIdle);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setValidationTimeout(validationTimeout);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setLeakDetectionThreshold(leakDetectionThreshold);
        dataSource.setMaxLifetime(maxLifetime);
        dataSource.setAutoCommit(autoCommit);
        logger.info("custom init datasource successful ...");
        return dataSource;
    }

    @Bean
    @Primary
    public JdbcTemplate getJdbcTemplate() {
        logger.info("add jdbc template to database ...");
        return new JdbcTemplate(dataSource());
    }

}
