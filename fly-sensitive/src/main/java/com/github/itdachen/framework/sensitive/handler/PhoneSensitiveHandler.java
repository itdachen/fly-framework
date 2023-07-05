package com.github.itdachen.framework.sensitive.handler;


import com.github.itdachen.framework.sensitive.ISensitiveHandler;

/**
 * 手机号码脱敏
 * Created by 王大宸 on 2023-07-04 16:50
 * Created with IntelliJ IDEA.
 */
public class PhoneSensitiveHandler implements ISensitiveHandler {

    @Override
    public String serialize(String value) {
        if (null == value || "".equals(value) || 7 >= value.length()) {
            return value;
        }
        return value.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

}
