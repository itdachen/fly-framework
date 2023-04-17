package com.github.itdachen.framework.datasource.encoder.processor.impl;

import com.github.itdachen.framework.crypto.aes.AesEncryptEncoder;
import com.github.itdachen.framework.datasource.constants.DataSourceKeyConstant;
import com.github.itdachen.framework.datasource.constants.DataSourceSecretKeyConstant;
import com.github.itdachen.framework.datasource.encoder.processor.DataSourceEncoderProcessor;
import org.springframework.stereotype.Service;

/**
 * Description: 数据库加密, AES 加密
 * Created by 王大宸 on 2023/03/28 16:58
 * Created with IntelliJ IDEA.
 */
@Service
public class AesDataSourceEncoderProcessor implements DataSourceEncoderProcessor {
    private static final String AES_PREFIX = DataSourceKeyConstant.AES + DataSourceKeyConstant.PREFIX;
    private static final String AES_SUFFIX = DataSourceKeyConstant.SUFFIX;

    private static final String SECRET_KEY = "#!SAGA2023*&@";

    @Override
    public String encrypt(String str) {
        final String encryptStr = AesEncryptEncoder.encryptStr(str, SECRET_KEY);
        return AES_PREFIX + encryptStr + AES_SUFFIX;
    }

    @Override
    public String decrypt(String str) {
        str = str.replace(AES_PREFIX, "");
        if (!str.endsWith(AES_SUFFIX)) {
            return str;
        }
        str = str.replace(AES_SUFFIX, "");
        return AesEncryptEncoder.decryptStr(str, SECRET_KEY);
    }

}
