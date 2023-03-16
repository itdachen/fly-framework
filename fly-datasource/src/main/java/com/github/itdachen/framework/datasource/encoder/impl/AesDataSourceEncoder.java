package com.github.itdachen.framework.datasource.encoder.impl;

import com.github.itdachen.framework.crypto.aes.AesEncryptEncoder;
import com.github.itdachen.framework.datasource.encoder.DataSourceEncoder;

/**
 * Description: AES 加解密
 * Created by 王大宸 on 2023/01/28 15:13
 * Created with IntelliJ IDEA.
 */
public class AesDataSourceEncoder implements DataSourceEncoder {

    private final String secretKey;

    public AesDataSourceEncoder(String secretKey) {
        this.secretKey = secretKey;
    }


    @Override
    public String decrypt(String str) {
        return AesEncryptEncoder.decryptStr(str, secretKey);
    }


    @Override
    public String encrypt(String str) {
        return AesEncryptEncoder.encryptStr(str, secretKey);
    }

}
