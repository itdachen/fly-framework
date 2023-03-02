package com.itdachen.framework.datasource.config;

import com.itdachen.framework.datasource.constants.AesKeyConstant;
import com.itdachen.framework.datasource.encoder.DataSourceEncoder;
import com.itdachen.framework.datasource.encoder.impl.AesDataSourceEncoder;
import com.itdachen.framework.datasource.encoder.impl.NoneDataSourceEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023/01/28 15:16
 * Created with IntelliJ IDEA.
 */
@Configuration
public class DatasourceEncoderBeanConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatasourceEncoderBeanConfig.class);


    /***
     * 数据库连接加密,默认不加密
     *
     * @author 王大宸
     * @date 2022/2/23 17:23
     * @param
     * @return com.itdachen.common.encryption.encoder.datasource.DataSourceEncoder
     */
    @Bean
    @ConditionalOnProperty(prefix = "config.datasource", name = "encoder", havingValue = "NONE", matchIfMissing = true)
    public DataSourceEncoder dataSourceEncoder() {
        logger.info("数据库连接未加密...");
        return new NoneDataSourceEncoder();
    }

    /***
     * 数据库 aes 加密方式(推荐)
     *
     * @author 王大宸
     * @date 2022/2/23 17:23
     * @param
     * @return com.itdachen.common.encryption.encoder.datasource.DataSourceEncoder
     */
    @Bean
    @ConditionalOnProperty(prefix = "config.datasource", name = "encoder", havingValue = "AES")
    public DataSourceEncoder aesDataSourceEncoder() {
        logger.info("数据库连接加密方式: AES");
        return new AesDataSourceEncoder(AesKeyConstant.SECRET_KEY);
    }

}
