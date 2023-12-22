package com.github.itdachen.boot.datasource.factory;

import com.github.itdachen.boot.datasource.processor.AbstractDataSourceEncryptProcessor;
import com.github.itdachen.boot.datasource.processor.encrypt.AesDataSourceEncryptProcessor;
import com.github.itdachen.boot.datasource.processor.encrypt.NoopDataSourceEncryptProcessor;
import com.github.itdachen.boot.autoconfigure.datasource.DataSourceProperties;
import com.github.itdachen.boot.autoconfigure.datasource.enums.DataSourceEncoderTypeEnum;
import org.springframework.stereotype.Component;

/**
 * Description: 数据库加密工厂
 * Created by 王大宸 on 2023-08-15 9:28
 * Created with IntelliJ IDEA.
 */
@Component
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
