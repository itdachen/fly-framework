package com.github.itdachen.framework.boot.security.validate.code.enums;

import com.github.itdachen.framework.boot.autoconfigure.security.constants.SecurityConstants;

/**
 * Description: 验证码类型
 * Created by 剑鸣秋朔 on 2021-11-26 22:40
 * Created with IntelliJ IDEA.
 */
public enum ValidateCodeType {

    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     *
     * @return
     */
    public abstract String getParamNameOnValidate();

}
