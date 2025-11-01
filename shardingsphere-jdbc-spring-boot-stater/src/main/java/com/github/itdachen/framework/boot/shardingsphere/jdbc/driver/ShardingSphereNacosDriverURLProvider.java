package com.github.itdachen.framework.boot.shardingsphere.jdbc.driver;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.utils.StringUtils;
import org.apache.shardingsphere.driver.jdbc.core.driver.ShardingSphereDriverURLProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 基于 SPI 机制修改 ShardingJDBC 底层，实现 Nacos 配置数据源
 * 基于 ApolloDriverURLProvider 写 Nacos 连接 ShardingJDBC 方式
 * 修改之后配置:
 * #spring:
 * #  datasource:
 * #    driver-class-name: org.apache.shardingsphere.driver.ShardingSphereDriver
 * #    url: jdbc:shardingsphere:nacos:127.0.0.1:8848:user-shardingjdbc.yaml?username=nacos&&password=nacos&&namespace=user-shardingjdbc
 * #
 * #    hikari:
 * #      pool-name: uni-user-pool
 * #      minimum-idle: 15
 * #      maximum-pool-size: 300
 * #      idle-timeout: 60000
 * #      connection-init-sql: select 1
 * #      connection-timeout: 4000
 * #      max-lifetime: 60000
 *
 * @author 王大宸
 * @date 2025/11/1 22:50
 */
public class ShardingSphereNacosDriverURLProvider implements ShardingSphereDriverURLProvider {
    private static Logger logger = LoggerFactory.getLogger(ShardingSphereNacosDriverURLProvider.class);

    private static final String NACOS_TYPE = "nacos:"; // 指定从 Nacos 中读取
    private static final String GROUP = "DEFAULT_GROUP"; // 默认分组

    @Override
    public boolean accept(String url) {
        return url != null && url.contains(NACOS_TYPE);
    }


    @Override
    public byte[] getContent(final String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        //得到例如：127.0.0.1:8848:user-shardingjdbc.yaml?username=nacos&&password=nacos&&namespace=user-shardingjdbc 格式的url
        String nacosUrl = url.substring(url.lastIndexOf(NACOS_TYPE) + NACOS_TYPE.length());
        /**
         * 得到三个字符串，分别是：
         * 127.0.0.1
         * 8848
         * user-shardingjdbc.yaml
         */
        String nacosStr[] = nacosUrl.split(":");
        String nacosFileStr = nacosStr[2];

        /**
         * 得到两个字符串
         * user-shardingjdbc.yaml
         * username=nacos&&password=nacos&&namespace=user-shardingjdbc
         */
        String nacosFileProp[] = nacosFileStr.split("\\?");
        String dataId = nacosFileProp[0];
        String acceptProp[] = nacosFileProp[1].split("&&");
        //这里获取到
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, nacosStr[0] + ":" + nacosStr[1]);
        for (String propertyName : acceptProp) {
            String[] propertyItem = propertyName.split("=");
            String key = propertyItem[0];
            String value = propertyItem[1];
            if ("username".equals(key)) {
                properties.setProperty(PropertyKeyConst.USERNAME, value);
            } else if ("password".equals(key)) {
                properties.setProperty(PropertyKeyConst.PASSWORD, value);
            } else if ("namespace".equals(key)) {
                properties.setProperty(PropertyKeyConst.NAMESPACE, value);
            }
        }
        ConfigService configService = null;
        try {
            configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, GROUP, 6000);
            logger.info("Load Nacos ShardingJDBC Info: " + content);
            return content.getBytes();
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }


}
