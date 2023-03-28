package com.github.itdachen.framework.datasource.enums;

/**
 * Description: 加密类型
 * Created by 王大宸 on 2023/03/26 22:24
 * Created with IntelliJ IDEA.
 */
public enum EncoderTypeKeyEnum {

    /**
     * AES 加密
     */
    AES("{aes}", "aes"),

    /**
     * 不加密
     */
    NOOP("{noop}", "noop"),

    ;

    /**
     * 加密类型
     */
    private final String encoder;

    /**
     * 处理类型前缀
     */
    private final String prefix;

    EncoderTypeKeyEnum(String encoder, String prefix) {
        this.encoder = encoder;
        this.prefix = prefix;
    }

    public String getEncoder() {
        return encoder;
    }

    public String getPrefix() {
        return prefix;
    }
}
