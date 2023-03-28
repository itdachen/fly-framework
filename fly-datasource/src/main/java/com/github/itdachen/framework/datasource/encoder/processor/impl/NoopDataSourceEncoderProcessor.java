package com.github.itdachen.framework.datasource.encoder.processor.impl;

import com.github.itdachen.framework.datasource.encoder.processor.DataSourceEncoderProcessor;
import org.springframework.stereotype.Service;

/**
 * Description: 默认, 不加密
 * Created by 王大宸 on 2023/03/28 17:00
 * Created with IntelliJ IDEA.
 */
@Service
public class NoopDataSourceEncoderProcessor implements DataSourceEncoderProcessor {
    @Override
    public String encrypt(String str) {
        return str;
    }

    @Override
    public String decrypt(String str) {
        return str;
    }
}
