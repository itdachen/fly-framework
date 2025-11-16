package com.github.itdachen.framework.boot.security.validate.code.sms;

/**
 * Description: 短信验证码发送接口
 * Created by 剑鸣秋朔 on 2021-11-27 10:27
 * Created with IntelliJ IDEA.
 */
public interface SmsCodeSender {

    void send(String mobile, String code);

}
