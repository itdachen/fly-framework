package com.github.itdachen.boot.datasource.dynamic.utils;

import com.github.itdachen.boot.autoconfigure.datasource.DataSourceProperties;
import com.github.itdachen.boot.autoconfigure.datasource.constants.DataSourceSecretKey;
import com.github.itdachen.boot.autoconfigure.datasource.entity.DataSourceInfo;
import com.github.itdachen.boot.autoconfigure.datasource.enums.DataSourceEncoderTypeEnum;
import com.github.itdachen.boot.datasource.crypto.processor.decrypt.AesDataSourceDecryptProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataSourceUtils
 *
 * @author 王大宸
 * @date 2023-12-30 19:39
 */
public class DataSourceUtils {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceUtils.class);


    public static String trim(String value) {
        if (null == value) {
            return null;
        }
        return value.trim().replace(" ", "");
    }


    public static List<String> toList(String str, String strByte) {
        if (isEmpty(str) || isEmpty(strByte)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(str.split(strByte)));
    }


    public static boolean isEmpty(String str) {
        return null == str || "null".equals(str.trim()) || "undefined".equals(str) || "".equals(str);
    }

    /***
     * 数据库连接解密
     *
     * @author 王大宸
     * @date 2023/9/11 15:50
     * @param dataSourceInfo dataSourceInfo
     * @return cn.edu.hubu.framework.autoconfigure.datasource.DataSourceInfo
     */
    public static DataSourceInfo decryptDataSource(DataSourceInfo dataSourceInfo,
                                                   DataSourceProperties cryptoConfiguration) {
        if (DataSourceEncoderTypeEnum.AES.equals(cryptoConfiguration.getEncoder())) {
            AesDataSourceDecryptProcessor processor = new AesDataSourceDecryptProcessor(cryptoConfiguration);

            dataSourceInfo.setKey(dataSourceInfo.getKey());
            dataSourceInfo.setDriverClassName(dataSourceInfo.getDriverClassName());

            /* 解密 */
            dataSourceInfo.setUrl(processor.decrypt(dataSourceInfo.getUrl()));
            dataSourceInfo.setUsername(processor.decrypt(dataSourceInfo.getUsername()));
            dataSourceInfo.setPassword(processor.decrypt(dataSourceInfo.getPassword()));

            return dataSourceInfo;
        }
        return dataSourceInfo;
    }

    /***
     * 获取加密配置文件
     *
     * @author 王大宸
     * @date 2023/9/11 16:08
     * @param environment environment
     * @return cn.edu.hubu.framework.autoconfigure.datasource.DataSourceCryptoProperties
     */
    public static DataSourceProperties getCryptoProperties(Environment environment) {
        /* 数据库加密配置 */
        BindResult<DataSourceProperties> cryptoProperties = Binder.get(environment)
                .bind("hubu.datasource", DataSourceProperties.class);

        DataSourceProperties cryptoConfiguration;
        if (!cryptoProperties.isBound()) {
            cryptoConfiguration = new DataSourceProperties();
            cryptoConfiguration.setEncoder(DataSourceEncoderTypeEnum.NOOP);
            cryptoConfiguration.setSecretKey(DataSourceSecretKey.SECRET_KEY);
        } else {
            cryptoConfiguration = cryptoProperties.get();
        }
        return cryptoConfiguration;
    }


}
