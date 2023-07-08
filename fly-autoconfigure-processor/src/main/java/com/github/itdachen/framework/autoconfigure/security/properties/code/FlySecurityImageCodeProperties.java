package com.github.itdachen.framework.autoconfigure.security.properties.code;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 图形验证码
 * Created by 王大宸 on 2022-09-23 9:57
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.security.code.image")
public class FlySecurityImageCodeProperties extends FlySecuritySmsCodeProperties {

    // 默认为 4 位数
    public FlySecurityImageCodeProperties() {
        setLength(4);
    }

    private int width = 67;

    private int height = 23;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
