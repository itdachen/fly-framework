package com.github.itdachen.framework.datasource.config;

import com.github.itdachen.framework.datasource.DataSourceEncoder;
import com.github.itdachen.framework.datasource.encoder.processor.DataSourceEncoderProcessor;
import com.github.itdachen.framework.datasource.encoder.DefaultDataSourceEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Description:
 * Created by 王大宸 on 2023/03/08 15:43
 * Created with IntelliJ IDEA.
 */
@Configuration
public class DataSourceBeanConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceBeanConfig.class);

    private final Map<String, DataSourceEncoderProcessor> encoderProcessor;
    private final DataSource dataSource;

    public DataSourceBeanConfig(DataSource dataSource, Map<String, DataSourceEncoderProcessor> encoderProcessor) {
        this.dataSource = dataSource;
        this.encoderProcessor = encoderProcessor;
    }

    /***
     * 增加Jdbc模板支持
     *
     * @author 王大宸
     * @date 2023/3/28 17:24
     * @return org.springframework.jdbc.core.JdbcTemplate
     */
    @Bean
    @Primary
    public JdbcTemplate getJdbcTemplate() {
        logger.error("数据库添加Jdbc模板支持...");
        return new JdbcTemplate(dataSource);
    }

    /***
     * 默认 加密/解密实现
     * 如果需要自己实现加解密方式, 实现 DataSourceEncoder 接口即可
     * @author 王大宸
     * @date 2023/3/28 17:24
     * @return com.github.itdachen.framework.datasource.DataSourceEncoder
     */
    @Bean
    @ConditionalOnMissingBean(DataSourceEncoder.class)
    public DataSourceEncoder smsCodeSender() {
        return new DefaultDataSourceEncoder(encoderProcessor);
    }


}
