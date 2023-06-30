package com.github.itdachen.framework.datasource.encoder;

import com.github.itdachen.framework.datasource.encoder.processor.DataSourceEncoderProcessor;
import com.github.itdachen.framework.datasource.enums.EncoderTypeKeyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Description: 数据库信息加解密处理
 * Created by 王大宸 on 2023/03/28 17:16
 * Created with IntelliJ IDEA.
 */
@Service
public class DefaultDataSourceEncoder {
    private static final Logger logger = LoggerFactory.getLogger(DefaultDataSourceEncoder.class);

    /**
     * 收集系统中所有的 {@link DataSourceEncoderProcessor} 接口的实现。
     */
    private final Map<String, DataSourceEncoderProcessor> encoderProcessor;

    public DefaultDataSourceEncoder(Map<String, DataSourceEncoderProcessor> encoderProcessor) {
        this.encoderProcessor = encoderProcessor;
    }


    /***
     * 加密
     *
     * @author 王大宸
     * @date 2022/1/5 15:46
     * @param str 需要加密的 url/username/password
     * @return java.lang.String
     */
    public String encrypt(String str, String type) {
        DataSourceEncoderProcessor processor = findDataSourceEncoderProcessor(type);
        if (null == processor) {
            logger.error("数据库连接加密: 加密处理器不存在");
            return str;
        }
        return processor.encrypt(str);
    }

    /***
     * 解密
     *
     * @author 王大宸
     * @date 2022/1/5 15:46
     * @param str 需要解密的 url/username/password
     * @return java.lang.String
     */
    public String decrypt(final String str) {
        DataSourceEncoderProcessor processor = findDataSourceEncoderProcessor(str);
        if (null == processor) {
           // logger.error("数据库连接解密: 解密处理器不存在");
            return str;
        }
        return processor.decrypt(str);
    }

    /***
     * 根据加密字符串, 获取 加密/解密 处理器
     *
     * @author 王大宸
     * @date 2023/3/28 17:10
     * @param str str
     * @return com.github.itdachen.framework.datasource.encoder.processor.DataSourceEncoderProcessor
     */
    private DataSourceEncoderProcessor findDataSourceEncoderProcessor(String str) {
        EncoderTypeKeyEnum[] values = EncoderTypeKeyEnum.values();
        String prefix = "";
        for (EncoderTypeKeyEnum value : values) {
            if (!str.startsWith(String.valueOf(value))) {
                continue;
            }
            prefix = String.valueOf(value).toLowerCase();
            break;
        }

        final String name = prefix + DataSourceEncoderProcessor.class.getSimpleName();
        return encoderProcessor.get(name);
    }

}
