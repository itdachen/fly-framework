package com.github.itdachen.framework.boot.security.validate.code.sms;

import com.github.itdachen.framework.boot.autoconfigure.security.properties.code.SecuritySmsCodeProperties;
import com.github.itdachen.framework.boot.security.validate.ValidateCode;
import com.github.itdachen.framework.boot.security.validate.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description: 短信验证码生成
 * Created by 剑鸣秋朔 on 2021-11-27 10:28
 * Created with IntelliJ IDEA.
 */
//@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    private final SecuritySmsCodeProperties securityProperties;

    public SmsCodeGenerator(SecuritySmsCodeProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getLength());
        return new ValidateCode(code, securityProperties.getExpireIn());
    }

}
