package com.github.itdachen.framework.boot.datasource.crypto.processor.encrypt;

import com.github.itdachen.framework.boot.datasource.crypto.processor.AbstractDataSourceEncryptProcessor;

/**
 * Description: 不加密
 * Created by 剑鸣秋朔 on 2023-08-15 9:41
 * Created with IntelliJ IDEA.
 */
public class NoopDataSourceEncryptProcessor extends AbstractDataSourceEncryptProcessor {


    @Override
    public String encrypt(String str) {
        return str;
    }

}
