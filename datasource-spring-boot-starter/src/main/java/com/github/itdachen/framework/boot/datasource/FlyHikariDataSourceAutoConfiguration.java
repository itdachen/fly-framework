package com.github.itdachen.framework.boot.datasource;

import com.github.itdachen.framework.boot.datasource.crypto.IDataSourceDecrypt;
import com.github.itdachen.framework.boot.datasource.hikari.FlyHikariDataSourceWrapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * FlyHikariDataSourceAutoConfiguration
 *
 * @author 王大宸
 * @date 2024-01-10 22:29
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource", name = "type", havingValue = "com.zaxxer.hikari.HikariDataSource")
public class FlyHikariDataSourceAutoConfiguration {

    private final IDataSourceDecrypt dataSourceDecrypt;

    public FlyHikariDataSourceAutoConfiguration(IDataSourceDecrypt dataSourceDecrypt) {
        this.dataSourceDecrypt = dataSourceDecrypt;
    }


    @Bean
    @Primary
    public FlyHikariDataSourceWrapper hikariDataSourceConfiguration() {
        return new FlyHikariDataSourceWrapper(dataSourceDecrypt);
    }

}
