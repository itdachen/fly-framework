package com.github.itdachen.framework.autoconfigure.security.properties.code;

import java.io.Serializable;

/**
 * Description:
 * Created by 王大宸 on 2023-07-09 15:17
 * Created with IntelliJ IDEA.
 */
public class ValidateCode implements Serializable {
    private static final long serialVersionUID = 8603556855503352733L;

    /**
     * 验证码长度
     */
    private int length = 6;

    /**
     * 验证码有效时间
     */
    private int expireIn = 60;

    /**
     * 需要拦截的地址
     */
    private String url;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
