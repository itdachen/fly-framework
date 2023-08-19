package com.github.itdachen.framework.datasource.factory;

import com.github.itdachen.framework.autoconfigure.datasource.DataSourceProperties;
import com.github.itdachen.framework.autoconfigure.datasource.enums.DataSourceEncoderTypeEnum;
import com.github.itdachen.framework.datasource.processor.AbstractDataSourceDecryptProcessor;
import com.github.itdachen.framework.datasource.processor.decrypt.AesDataSourceDecryptProcessor;
import com.github.itdachen.framework.datasource.processor.decrypt.NoopDataSourceDecryptProcessor;
import org.springframework.stereotype.Component;

/**
 * Description: 解密工厂
 * Created by 王大宸 on 2023-08-15 9:58
 * Created with IntelliJ IDEA.
 */
@Component
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
