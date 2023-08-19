package com.github.itdachen.framework.datasource.processor.encrypt;

import com.github.itdachen.framework.autoconfigure.datasource.DataSourceProperties;
import com.github.itdachen.framework.crypto.aes.AesEncryptEncoder;
import com.github.itdachen.framework.datasource.processor.AbstractDataSourceEncryptProcessor;

/**
 * Description: AES 加密
 * Created by 王大宸 on 2023-08-15 9:42
 * Created with IntelliJ IDEA.
 */
public class AesDataSourceEncryptProcessor extends AbstractDataSourceEncryptProcessor {

    private final DataSourceProperties dataSourceProperties;

    public AesDataSourceEncryptProcessor(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }


    @Override
    public String encrypt(String str) {
        return AesEncryptEncoder.encryptStr(str, dataSourceProperties.getSecretKey());
    }


}
