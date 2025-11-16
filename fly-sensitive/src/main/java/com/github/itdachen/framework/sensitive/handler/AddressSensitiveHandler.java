package com.github.itdachen.framework.sensitive.handler;

import com.github.itdachen.framework.sensitive.ISensitiveHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * Description: 地址
 * 只显示到地区，不显示详细地址，比如：北京市海淀区****
 * Created by 剑鸣秋朔 on 2023-07-05 16:50
 * Created with IntelliJ IDEA.
 */
public class AddressSensitiveHandler implements ISensitiveHandler {

    @Override
    public String serialize(String value) {
        int sensitiveSize = 6;
        if (StringUtils.isBlank(value)) {
            return "";
        }
        int length = StringUtils.length(value);
        if (sensitiveSize > length) {
            return value;
        }
        return StringUtils.rightPad(StringUtils.left(value, length - sensitiveSize), length, "*");
    }


}
