package com.github.itdachen.framework.boot.datasource.crypto.processor.decrypt;

import com.github.itdachen.framework.boot.datasource.crypto.processor.AbstractDataSourceDecryptProcessor;

/**
 * Description: 不加密 --> 不解密
 * Created by 王大宸 on 2023-08-15 9:53
 * Created with IntelliJ IDEA.
 */
public class NoopDataSourceDecryptProcessor extends AbstractDataSourceDecryptProcessor {

    @Override
    public String decrypt(String str) {
        return str;
    }

}
