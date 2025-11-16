package com.github.itdachen.framework.boot.security.validate.code.sms;

import com.github.itdachen.framework.boot.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.framework.boot.security.validate.ValidateCode;
import com.github.itdachen.framework.boot.security.validate.code.processor.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description: 短信验证码处理器
 * Created by 剑鸣秋朔 on 2021-11-27 15:28
 * Created with IntelliJ IDEA.
 */
//@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}
