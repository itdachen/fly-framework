package com.github.itdachen.framework.boot.datasource.crypto.factory;

import com.github.itdachen.framework.boot.autoconfigure.datasource.DataSourceProperties;
import com.github.itdachen.framework.boot.autoconfigure.datasource.enums.DataSourceEncoderTypeEnum;
import com.github.itdachen.framework.boot.datasource.crypto.processor.AbstractDataSourceEncryptProcessor;
import com.github.itdachen.framework.boot.datasource.crypto.processor.encrypt.AesDataSourceEncryptProcessor;
import com.github.itdachen.framework.boot.datasource.crypto.processor.encrypt.NoopDataSourceEncryptProcessor;

/**
 * Description: 数据库加密工厂
 * Created by 王大宸 on 2023-08-15 9:28
 * Created with IntelliJ IDEA.
 */
public class DataSourceEncryptFactory {

    private final DataSourceProperties dataSourceProperties;

    public DataSourceEncryptFactory(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }


    public AbstractDataSourceEncryptProcessor build() {
        if (DataSourceEncoderTypeEnum.AES == dataSourceProperties.getEncoder()) {
            return new AesDataSourceEncryptProcessor(dataSourceProperties);
        }
        return new NoopDataSourceEncryptProcessor();
    }


}
