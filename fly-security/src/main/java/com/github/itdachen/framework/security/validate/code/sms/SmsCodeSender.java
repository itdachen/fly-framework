package com.github.itdachen.framework.security.validate.code.sms;

/**
 * Description: 短信验证码发送接口
 * Created by 王大宸 on 2021-11-27 10:27
 * Created with IntelliJ IDEA.
 */
public interface SmsCodeSender {

    void send(String mobile, String code);

}
