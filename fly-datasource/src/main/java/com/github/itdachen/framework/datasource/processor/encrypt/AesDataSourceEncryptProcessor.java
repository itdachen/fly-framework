package com.github.itdachen.framework.datasource.processor.encrypt;

import com.github.itdachen.framework.autoconfigure.datasource.DataSourceCryptoConfiguration;
import com.github.itdachen.framework.crypto.aes.AesEncryptEncoder;
import com.github.itdachen.framework.datasource.processor.AbstractDataSourceEncryptProcessor;

/**
 * Description: AES 加密
 * Created by 王大宸 on 2023-08-15 9:42
 * Created with IntelliJ IDEA.
 */
public class AesDataSourceEncryptProcessor extends AbstractDataSourceEncryptProcessor {

    private final DataSourceCryptoConfiguration cryptoConfiguration;

    public AesDataSourceEncryptProcessor(DataSourceCryptoConfiguration cryptoConfiguration) {
        this.cryptoConfiguration = cryptoConfiguration;
    }


    @Override
    public String encrypt(String str) {
        return AesEncryptEncoder.encryptStr(str, cryptoConfiguration.getSecretKey());
    }


}
