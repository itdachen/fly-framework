package com.github.itdachen.framework.sensitive.handler;

import com.github.itdachen.framework.sensitive.ISensitiveHandler;

/**
 * 全部脱敏, 全隐藏，如： ***
 * Created by 剑鸣秋朔 on 2023-07-05 16:45
 * Created with IntelliJ IDEA.
 */
public class AllMaskSensitiveHandler implements ISensitiveHandler {

    @Override
    public String serialize(String value) {
        if (null == value || 0 == value.trim().length()) {
            return value;
        }
        if (2 >= value.trim().length()) {
            return "**";
        }
        return "********";
    }

}
