package com.github.itdachen.framework.datasource.encoder;

import com.github.itdachen.framework.datasource.DataSourceEncrypt;
import com.github.itdachen.framework.datasource.enums.EncoderTypeKeyEnum;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Created by 王大宸 on 2023/04/17 22:44
 * Created with IntelliJ IDEA.
 */
@Service
public class DataSourceEncryptProcessor implements DataSourceEncrypt {
    private final DefaultDataSourceEncoder defaultDataSourceEncoder;

    public DataSourceEncryptProcessor(DefaultDataSourceEncoder defaultDataSourceEncoder) {
        this.defaultDataSourceEncoder = defaultDataSourceEncoder;
    }


    @Override
    public String encrypt(String str, String type) {
        return defaultDataSourceEncoder.encrypt(str, type);
    }

}
