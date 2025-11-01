package com.github.itdachen.framework.boot.datasource.dynamic;

import com.github.itdachen.framework.boot.autoconfigure.datasource.DataSourceProperties;
import com.github.itdachen.framework.boot.autoconfigure.datasource.DynamicDataSourceProperties;
import com.github.itdachen.framework.boot.autoconfigure.datasource.constants.DataSourceSecretKey;
import com.github.itdachen.framework.boot.autoconfigure.datasource.entity.DataSourceInfo;
import com.github.itdachen.framework.boot.autoconfigure.datasource.enums.DataSourceEncoderTypeEnum;
import com.github.itdachen.framework.boot.datasource.dynamic.utils.DataSourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Map;

/**
 * 自动注册
 *
 * @author 王大宸
 * @date 2023-12-30 19:29
 */
public class DynamicDataSourceRegister implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    /**
     * 数据源配置
     */
    private DynamicDataSourceProperties dynamicDataSourceProperties;

    /**
     * 解密配置
     */
    private DataSourceProperties cryptoConfiguration;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        /* 所有的数据源节点 */
        List<String> dbs = DataSourceUtils.toList(dynamicDataSourceProperties.getNames(), ",");
        if (dbs.isEmpty()) {
            logger.warn("At least one data source node names to be configured ...");
        }

        logger.info("datasource encoder type: {}", cryptoConfiguration.getEncoder());

        Map<String, DataSourceInfo> dataSourceMap = dynamicDataSourceProperties.getDynamic();

        DataSourceInfo dataSource;
        GenericBeanDefinition beandefinition;
        MutablePropertyValues propertyValues;
        for (String dbKey : dbs) {
            dbKey = DataSourceUtils.trim(dbKey);

            // 数据库连接解密
            dataSource = DataSourceUtils.decryptDataSource(dataSourceMap.get(dbKey), cryptoConfiguration);

            if (null == dataSource) {
                logger.warn("dataSource [{}] is null", dbKey);
                continue;
            }

            beandefinition = new GenericBeanDefinition();

            propertyValues = beandefinition.getPropertyValues();
            propertyValues.add("name", dbKey);
            propertyValues.add("driverClassName", dataSource.getDriverClassName());
            propertyValues.add("url", dataSource.getUrl());
            propertyValues.add("username", dataSource.getUsername());
            propertyValues.add("password", dataSource.getPassword());


//            DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
//            if (null != druidProperties) {
//                propertyValues.add("druid", druidProperties.dataSource(druidDataSource));
//            }


            beandefinition.setPropertyValues(propertyValues);
            beandefinition.setBeanClassName(dataSource.getKey());
            registry.registerBeanDefinition(dbKey, beandefinition);

        }

        logger.info("Dynamic DataSource Registry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {

        /* 多数据源配置 */
        BindResult<DynamicDataSourceProperties> bindResult = Binder.get(environment)
                .bind("spring.datasource", DynamicDataSourceProperties.class);
        dynamicDataSourceProperties = bindResult.get();

        /* 数据库加密配置 */
        BindResult<DataSourceProperties> cryptoProperties = Binder.get(environment)
                .bind("fly.datasource", DataSourceProperties.class);
        if (!cryptoProperties.isBound()) {
            cryptoConfiguration = new DataSourceProperties();
            cryptoConfiguration.setEncoder(DataSourceEncoderTypeEnum.NOOP);
            cryptoConfiguration.setSecretKey(DataSourceSecretKey.SECRET_KEY);
        } else {
            cryptoConfiguration = cryptoProperties.get();
        }

    }

}
