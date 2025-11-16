package com.github.itdachen.framework.sensitive.handler;

import com.github.itdachen.framework.sensitive.ISensitiveHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * 邮箱脱敏
 * Created by 剑鸣秋朔 on 2023-07-05 16:39
 * Created with IntelliJ IDEA.
 */
public class EmailSensitiveHandler implements ISensitiveHandler {

    @Override
    public String serialize(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        int index = StringUtils.indexOf(value, "@");
        if (index <= 3) {
            return value;
        }
        return StringUtils.rightPad(StringUtils.left(value, 3), index, "*")
                .concat(StringUtils.mid(value, index, StringUtils.length(value)));
    }

}
