package com.github.itdachen.boot.datasource;

import com.github.itdachen.boot.autoconfigure.datasource.DataSourceProperties;
import com.github.itdachen.boot.datasource.crypto.IDataSourceDecrypt;
import com.github.itdachen.boot.datasource.crypto.IDataSourceEncrypt;
import com.github.itdachen.boot.datasource.crypto.factory.DataSourceDecryptFactory;
import com.github.itdachen.boot.datasource.crypto.factory.DataSourceEncryptFactory;
import com.github.itdachen.boot.datasource.crypto.strategy.DataSourceDecryptStrategy;
import com.github.itdachen.boot.datasource.crypto.strategy.DataSourceEncryptStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DataSourceDecryptAutoConfiguration
 *
 * @author 王大宸
 * @date 2023-12-27 20:54
 */
@Configuration
public class DataSourceDecryptAutoConfiguration {

    private final DataSourceProperties dataSourceProperties;

    public DataSourceDecryptAutoConfiguration(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    /***
     * 解密工厂
     *
     * @author 王大宸
     * @date 2023/12/27 20:56
     * @return com.github.itdachen.boot.datasource.crypto.factory.DataSourceDecryptFactory
     */
    @Bean
    public DataSourceDecryptFactory dataSourceDecryptFactory() {
        return new DataSourceDecryptFactory(dataSourceProperties);
    }

    /***
     * 加密工厂
     *
     * @author 王大宸
     * @date 2023/12/27 21:03
     * @return com.github.itdachen.boot.datasource.crypto.factory.DataSourceEncryptFactory
     */
    @Bean
    public DataSourceEncryptFactory dataSourceEncryptFactory() {
        return new DataSourceEncryptFactory(dataSourceProperties);
    }

    /***
    * 默认解密方式
    *
    * @author 王大宸
    * @date 2023/12/27 21:05
    * @return com.github.itdachen.boot.datasource.crypto.IDataSourceDecrypt
    */
    @Bean
    @ConditionalOnMissingBean(IDataSourceDecrypt.class)
    public IDataSourceDecrypt dataSourceDecrypt() {
        return new DataSourceDecryptStrategy(dataSourceDecryptFactory());
    }

    /***
    * 默认加密方策略
    *
    * @author 王大宸
    * @date 2023/12/27 21:05
    * @return com.github.itdachen.boot.datasource.crypto.IDataSourceEncrypt
    */
    @Bean
    @ConditionalOnMissingBean(IDataSourceEncrypt.class)
    public IDataSourceEncrypt dataSourceEncrypt() {
        return new DataSourceEncryptStrategy(dataSourceEncryptFactory());
    }

}
