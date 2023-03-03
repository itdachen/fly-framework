package com.itdachen.framework.security.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 默认短信发送实现,打印到控制台
 * Created by 王大宸 on 2021-11-27 10:29
 * Created with IntelliJ IDEA.
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSmsCodeSender.class);

    @Override
    public void send(String mobile, String code) {
        logger.info("向手机 " + mobile + " 发送短信验证码: " + code);
    }

}
