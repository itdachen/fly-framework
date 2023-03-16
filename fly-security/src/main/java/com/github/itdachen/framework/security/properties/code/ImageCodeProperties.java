package com.github.itdachen.framework.security.properties.code;

/**
 * Description: 图形验证码
 * Created by 王大宸 on 2022-09-23 9:57
 * Created with IntelliJ IDEA.
 */
public class ImageCodeProperties extends SmsCodeProperties {

    // 默认为 4 位数
    public ImageCodeProperties() {
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
