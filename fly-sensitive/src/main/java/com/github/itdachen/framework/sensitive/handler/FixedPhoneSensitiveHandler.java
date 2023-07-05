package com.github.itdachen.framework.sensitive.handler;

import com.github.itdachen.framework.sensitive.ISensitiveHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * Description: 座机号
 * Created by 王大宸 on 2023-07-05 16:48
 * Created with IntelliJ IDEA.
 */
public class FixedPhoneSensitiveHandler implements ISensitiveHandler {

    @Override
    public String serialize(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        return StringUtils.left(value, 4).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(value, 2), StringUtils.length(value), "*"), "****"));
    }

}
