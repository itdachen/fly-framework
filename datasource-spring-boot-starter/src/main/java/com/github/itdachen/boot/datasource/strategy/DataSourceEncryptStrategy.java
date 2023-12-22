package com.github.itdachen.boot.datasource.strategy;

import com.github.itdachen.boot.datasource.IDataSourceEncrypt;
import com.github.itdachen.boot.datasource.factory.DataSourceEncryptFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: 不加密
 * Created by 王大宸 on 2023-08-15 9:32
 * Created with IntelliJ IDEA.
 */
@Service
public class DataSourceEncryptStrategy implements IDataSourceEncrypt {

    @Autowired
    private DataSourceEncryptFactory encryptFactory;


    @Override
    public String encrypt(String str) {
        return encryptFactory.build().encrypt(str);
    }
}
