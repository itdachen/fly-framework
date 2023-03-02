package com.itdachen.framework.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.itdachen.framework.datasource.encoder.DataSourceEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Description: 阿里巴巴开源数据源自定义配置,连接地址可以加密
 * Created by 王大宸 on 2023/02/07 14:24
 * Created with IntelliJ IDEA.
 */
@Configuration
@SuppressWarnings("all")
@ConditionalOnProperty(prefix = "spring.datasource", name = "type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
public class DruidDataSourceConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DruidDataSourceConfiguration.class);

    @Autowired
    private DataSourceEncoder dataSourceEncoder;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.druid.filters}")
    private String filters;

    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("{spring.datasource.druid.connectionProperties}")
    private String connectionProperties;

    /***
     * 数据库连接解密
     *
     * @author 王大宸
     * @date 2023/2/7 14:36
     * @return javax.sql.DataSource
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    @ConditionalOnProperty(prefix = "config.datasource", name = "encoder", havingValue = "AES")
    public DataSource dataSource() {
        logger.info("正在配置数据库连接池...");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(driverClassName);

        // 数据库连接配置
        datasource.setUrl(dataSourceEncoder.decrypt(dbUrl));
        datasource.setUsername(dataSourceEncoder.decrypt(username));
        datasource.setPassword(dataSourceEncoder.decrypt(password));

        // 数据库连接池配置
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("数据库过滤器配置失败：", e);
        }
        datasource.setConnectionProperties(connectionProperties);
        logger.info("数据库连接池配置完毕！！！");
        return datasource;
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
        return new JdbcTemplate(dataSource());
    }

    /***
     * 功能说明：注册Servlet信息，配置数据库监控视图
     *
     * @author 王大宸
     * @date 2020/10/11 15:32
     * @param
     * @return org.springframework.boot.web.servlet.ServletRegistrationBean<com.alibaba.druid.support.http.StatViewServlet>
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {
        logger.info("正在注册Servlet信息，配置数据库监控视图...");
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        logger.info("数据库数据库监控视图配置完成");
        return servletRegistrationBean;
    }

    /***
     * 功能说明：注册Filter信息, 数据库监控拦截器
     *
     * @author 王大宸
     * @date 2020/10/11 15:32
     * @param
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean<com.alibaba.druid.support.http.WebStatFilter>
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        logger.info("正在注册Filter信息, 数据库监控拦截器...");
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        filterRegistrationBean.addInitParameter("DruidWebStatFilter", "/*");
        logger.info("数据库监控拦截器配置完成");
        return filterRegistrationBean;
    }


}
