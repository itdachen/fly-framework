package com.github.itdachen.boot.datasource.crypto.factory;

import com.github.itdachen.boot.autoconfigure.datasource.DataSourceProperties;
import com.github.itdachen.boot.autoconfigure.datasource.enums.DataSourceEncoderTypeEnum;
import com.github.itdachen.boot.datasource.crypto.processor.AbstractDataSourceDecryptProcessor;
import com.github.itdachen.boot.datasource.crypto.processor.decrypt.AesDataSourceDecryptProcessor;
import com.github.itdachen.boot.datasource.crypto.processor.decrypt.NoopDataSourceDecryptProcessor;

/**
 * Description: 解密工厂
 * Created by 王大宸 on 2023-08-15 9:58
 * Created with IntelliJ IDEA.
 */
//@Component
public class DataSourceDecryptFactory {

    private final DataSourceProperties dataSourceProperties;

    public DataSourceDecryptFactory(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }


    public AbstractDataSourceDecryptProcessor build() {
        if (DataSourceEncoderTypeEnum.AES == dataSourceProperties.getEncoder()) {
            return new AesDataSourceDecryptProcessor(dataSourceProperties);
        }
        return new NoopDataSourceDecryptProcessor();
    }


}
