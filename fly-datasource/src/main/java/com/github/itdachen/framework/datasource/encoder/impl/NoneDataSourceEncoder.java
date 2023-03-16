package com.github.itdachen.framework.datasource.encoder.impl;

import com.github.itdachen.framework.datasource.encoder.DataSourceEncoder;

/**
 * Description: 不加密
 * Created by 王大宸 on 2023/01/28 15:12
 * Created with IntelliJ IDEA.
 */
public class NoneDataSourceEncoder implements DataSourceEncoder {
    @Override
    public String encrypt(String str) {
        return str;
    }

    @Override
    public String decrypt(String str) {
        return str;
    }
}
