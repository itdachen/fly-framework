package com.github.itdachen.boot.datasource.processor.encrypt;

import com.github.itdachen.boot.datasource.processor.AbstractDataSourceEncryptProcessor;

/**
 * Description: 不加密
 * Created by 王大宸 on 2023-08-15 9:41
 * Created with IntelliJ IDEA.
 */
public class NoopDataSourceEncryptProcessor extends AbstractDataSourceEncryptProcessor {


    @Override
    public String encrypt(String str) {
        return str;
    }

}
