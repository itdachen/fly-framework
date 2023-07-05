package com.github.itdachen.framework.sensitive.handler;

import com.github.itdachen.framework.sensitive.ISensitiveHandler;
import com.github.itdachen.framework.sensitive.utils.MaskUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 汉字掩码
 * 0-1字，如：用（用）
 * 2字，如：用于（*于）
 * 3-4字，如：用于掩（用*掩）、用于掩码（用**码）
 * 5-6字，如：用于掩码测（用于*码测）、用于掩码测试（用于**测试）
 * 大于6字，如：用于掩码测试的字符串（用于掩****字符串）
 * Created by 王大宸 on 2023-07-05 16:57
 * Created with IntelliJ IDEA.
 */
public class ChineseMaskSensitiveHandler implements ISensitiveHandler {

    @Override
    public String serialize(String value) {
        int lenth = StringUtils.length(value);
        return switch (lenth) {
            case 0, 1 -> value;
            case 2 -> "*" + value.charAt(1);
            case 3, 4 -> MaskUtils.wordMask(value, 1, 1, "*");
            case 5, 6, 7 -> MaskUtils.wordMask(value, 2, 2, "*");
            default -> MaskUtils.wordMask(value, 3, 3, "*");
        };
    }

}
