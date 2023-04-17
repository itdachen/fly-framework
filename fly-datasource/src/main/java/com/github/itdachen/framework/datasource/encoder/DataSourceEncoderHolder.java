package com.github.itdachen.framework.datasource.encoder;

import org.springframework.stereotype.Service;

/**
 * Description:
 * Created by 王大宸 on 2023/04/17 23:05
 * Created with IntelliJ IDEA.
 */
@Service
public class DataSourceEncoderHolder implements DataSourceEncoder {
    private final DefaultDataSourceEncoder defaultDataSourceEncoder;

    public DataSourceEncoderHolder(DefaultDataSourceEncoder defaultDataSourceEncoder) {
        this.defaultDataSourceEncoder = defaultDataSourceEncoder;
    }

    @Override
    public String decrypt(String str) {
        return defaultDataSourceEncoder.decrypt(str);
    }

}
