package com.github.itdachen.framework.datasource.encoder.processor.impl;

import com.github.itdachen.framework.datasource.constants.DataSourceKeyConstant;
import com.github.itdachen.framework.datasource.encoder.processor.DataSourceEncoderProcessor;
import org.springframework.stereotype.Service;

/**
 * Description: 默认, 不加密
 * Created by 王大宸 on 2023/03/28 17:00
 * Created with IntelliJ IDEA.
 */
@Service
public class NoopDataSourceEncoderProcessor implements DataSourceEncoderProcessor {
    private static final String NOOP_PREFIX = DataSourceKeyConstant.NOOP + DataSourceKeyConstant.PREFIX;
    private static final String NOOP_SUFFIX = DataSourceKeyConstant.SUFFIX;
    private static final String NOOP_ENCODER = NOOP_PREFIX + "%S" + NOOP_SUFFIX;


    @Override
    public String encrypt(String str) {
        return String.format(NOOP_ENCODER, str);
    }

    @Override
    public String decrypt(String str) {
        if (str.startsWith(NOOP_PREFIX)) {
            return str.replace(NOOP_PREFIX, "").replace(NOOP_SUFFIX, "");
        }
        return str;
    }
}
