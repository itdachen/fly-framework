package com.github.itdachen.framework.sensitive.handler;

import com.github.itdachen.framework.sensitive.ISensitiveHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * Description: 身份证号码脱敏
 * Created by 剑鸣秋朔 on 2023-07-04 16:51
 * Created with IntelliJ IDEA.
 */
public class IdCardSensitiveHandler implements ISensitiveHandler {

    @Override
    public String serialize(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        return value.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

}
