package com.github.itdachen.boot.datasource.crypto.strategy;

import com.github.itdachen.boot.datasource.crypto.IDataSourceEncrypt;
import com.github.itdachen.boot.datasource.crypto.factory.DataSourceEncryptFactory;

/**
 * Description: 不加密
 * Created by 王大宸 on 2023-08-15 9:32
 * Created with IntelliJ IDEA.
 */
public class DataSourceEncryptStrategy implements IDataSourceEncrypt {

    private final DataSourceEncryptFactory encryptFactory;

    public DataSourceEncryptStrategy(DataSourceEncryptFactory encryptFactory) {
        this.encryptFactory = encryptFactory;
    }


    @Override
    public String encrypt(String str) {
        return encryptFactory.build().encrypt(str);
    }
}
