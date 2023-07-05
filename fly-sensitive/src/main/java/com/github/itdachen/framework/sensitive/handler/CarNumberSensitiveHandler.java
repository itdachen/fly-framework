package com.github.itdachen.framework.sensitive.handler;

import com.github.itdachen.framework.sensitive.ISensitiveHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * 车牌号
 * Created by 王大宸 on 2023-07-05 16:13
 * Created with IntelliJ IDEA.
 */
public class CarNumberSensitiveHandler implements ISensitiveHandler {

    @Override
    public String serialize(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        return value.substring(0, 3) + "*" + value.substring(4);
    }

}
