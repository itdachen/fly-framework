package com.github.itdachen.framework.boot.datasource.crypto.strategy;


import com.github.itdachen.framework.boot.datasource.crypto.IDataSourceDecrypt;
import com.github.itdachen.framework.boot.datasource.crypto.factory.DataSourceDecryptFactory;

/**
 * Description: 解密处理
 * Created by 剑鸣秋朔 on 2023-08-15 9:32
 * Created with IntelliJ IDEA.
 */
public class DataSourceDecryptStrategy implements IDataSourceDecrypt {

    private final DataSourceDecryptFactory dataSourceDecryptFactory;

    public DataSourceDecryptStrategy(DataSourceDecryptFactory dataSourceDecryptFactory) {
        this.dataSourceDecryptFactory = dataSourceDecryptFactory;
    }

    @Override
    public String decrypt(String str) {
        return dataSourceDecryptFactory.build().decrypt(str);
    }

}
