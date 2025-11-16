package com.github.itdachen.framework.sensitive.handler;

import com.github.itdachen.framework.sensitive.ISensitiveHandler;

/**
 * 卡号脱敏, 身份证号码, 银行卡号 等
 * Created by 剑鸣秋朔 on 2023-07-05 16:41
 * Created with IntelliJ IDEA.
 */
public class CardNoSensitiveHandler implements ISensitiveHandler {

    @Override
    public String serialize(String value) {
        if ((value == null) || (value.trim().length() <= 8)) {
            return value;
        }
        value = value.trim();
        int length = value.length();
        String firstFourNo = value.substring(0, 4);
        String lastFourNo = value.substring(length - 4);
        StringBuffer mask = new StringBuffer();
        for (int i = 0; i < length - 8; i++) {
            mask.append("*");
        }
        return firstFourNo + mask + lastFourNo;
    }

}
