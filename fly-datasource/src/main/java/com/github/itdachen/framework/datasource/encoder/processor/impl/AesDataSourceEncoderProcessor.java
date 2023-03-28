package com.github.itdachen.framework.datasource.encoder.processor.impl;

import com.github.itdachen.framework.crypto.aes.AesEncryptEncoder;
import com.github.itdachen.framework.datasource.constants.DataSourceKeyConstant;
import com.github.itdachen.framework.datasource.encoder.processor.DataSourceEncoderProcessor;
import org.springframework.stereotype.Service;

/**
 * Description: 数据库加密, AES 加密
 * Created by 王大宸 on 2023/03/28 16:58
 * Created with IntelliJ IDEA.
 */
@Service
public class AesDataSourceEncoderProcessor implements DataSourceEncoderProcessor {

    private final String secretKey;

    public AesDataSourceEncoderProcessor() {
        this.secretKey = DataSourceKeyConstant.SECRET_KEY;
    }


    @Override
    public String encrypt(String str) {
        return AesEncryptEncoder.encryptStr(str, secretKey);
    }

    @Override
    public String decrypt(String str) {
        return AesEncryptEncoder.decryptStr(str, secretKey);
    }
}
