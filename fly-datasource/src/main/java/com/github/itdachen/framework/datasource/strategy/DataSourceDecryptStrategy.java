package com.github.itdachen.framework.datasource.strategy;

import com.github.itdachen.framework.datasource.IDataSourceDecrypt;
import com.github.itdachen.framework.datasource.factory.DataSourceDecryptFactory;
import org.springframework.stereotype.Service;

/**
 * Description: 解密处理
 * Created by 王大宸 on 2023-08-15 9:32
 * Created with IntelliJ IDEA.
 */
@Service
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
