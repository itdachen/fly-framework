package com.github.itdachen.framework.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.jakarta.StatViewServlet;
import com.alibaba.druid.support.jakarta.WebStatFilter;
import com.github.itdachen.framework.datasource.IDataSourceDecrypt;
import com.github.itdachen.framework.datasource.factory.DataSourceDecryptFactory;
import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
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
    private IDataSourceDecrypt dataSourceDecrypt;

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
            final String str = dataSourceDecrypt.decrypt(username);
            super.setUsername(str);
        }

        if (super.getPassword() == null) {
            final String str = dataSourceDecrypt.decrypt(password);
            super.setPassword(str);
        }

        if (super.getUrl() == null) {
            final String str = dataSourceDecrypt.decrypt(url);
            super.setUrl(str);
        }

        if (super.getDriverClassName() == null) {
            final String str = dataSourceDecrypt.decrypt(driverClassName);
            super.setDriverClassName(str);
        }
    }


    /***
     * 注册Servlet信息，配置数据库监控视图
     *
     * @author 王大宸
     * @date 2020/10/11 15:32
     * @return org.springframework.boot.web.servlet.ServletRegistrationBean<com.alibaba.druid.support.http.StatViewServlet>
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        return servletRegistrationBean;
    }

    /***
     * 注册Filter信息, 数据库监控拦截器
     *
     * @author 王大宸
     * @date 2020/10/11 15:32
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean<com.alibaba.druid.support.http.WebStatFilter>
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        filterRegistrationBean.addInitParameter("DruidWebStatFilter", "/*");
        return filterRegistrationBean;
    }

    /**
     * 去除广告
     *
     * @param properties
     * @return
     */
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Bean
//    @ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled", havingValue = "true", matchIfMissing = true)
//    public FilterRegistrationBean removeDruidAdFilterRegistrationBean(DruidStatProperties properties) {
//        // 获取web监控页面的参数
//        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
//        // 提取common.js的配置路径
//        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
//        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
//
//        final String filePath = "support/http/resources/js/common.js";
//
//        // 创建filter进行过滤
//        Filter filter = new Filter() {
//            @Override
//            public void init(FilterConfig filterConfig) throws ServletException {
//            }
//
//            @Override
//            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//                    throws IOException, ServletException {
//                chain.doFilter(request, response);
//                // 重置缓冲区，响应头不会被重置
//                response.resetBuffer();
//                // 获取common.js
//                String text = Utils.readFromResource(filePath);
//                // 正则替换banner, 除去底部的广告信息
//                text = text.replaceAll("<a.*?banner\"></a><br/>", "");
//                text = text.replaceAll("powered.*?shrek.wang</a>", "");
//                response.getWriter().write(text);
//            }
//
//            @Override
//            public void destroy() {
//            }
//        };
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(filter);
//        registrationBean.addUrlPatterns(commonJsPattern);
//        return registrationBean;
//    }

}
