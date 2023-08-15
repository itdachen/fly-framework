package com.github.itdachen.framework.datasource.processor.decrypt;

import com.github.itdachen.framework.autoconfigure.datasource.DataSourceCryptoConfiguration;
import com.github.itdachen.framework.crypto.aes.AesEncryptEncoder;
import com.github.itdachen.framework.datasource.processor.AbstractDataSourceDecryptProcessor;

/**
 * Description: AES 解密
 * Created by 王大宸 on 2023-08-15 9:53
 * Created with IntelliJ IDEA.
 */
public class AesDataSourceDecryptProcessor extends AbstractDataSourceDecryptProcessor {
    private final DataSourceCryptoConfiguration cryptoConfiguration;

    public AesDataSourceDecryptProcessor(DataSourceCryptoConfiguration cryptoConfiguration) {
        this.cryptoConfiguration = cryptoConfiguration;
    }

    @Override
    public String decrypt(String str) {
        return AesEncryptEncoder.decryptStr(str, cryptoConfiguration.getSecretKey());
    }

}
