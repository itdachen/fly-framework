package com.github.itdachen.framework.boot.autoconfigure.datasource;

import com.github.itdachen.framework.boot.autoconfigure.datasource.enums.DataSourceEncoderTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 数据库加密配置
 * Created by 王大宸 on 2023-08-15 9:19
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.datasource")
public class DataSourceProperties {

    /**
     * 默认加密秘钥
     */
    private String secretKey = "#Fly!SAGA2020*&@";

    /**
     * 数据库默认加密类型
     */
    private DataSourceEncoderTypeEnum encoder = DataSourceEncoderTypeEnum.NOOP;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public DataSourceEncoderTypeEnum getEncoder() {
        return encoder;
    }

    public void setEncoder(DataSourceEncoderTypeEnum encoder) {
        this.encoder = encoder;
    }
}
