package com.github.itdachen.framework.boot.autoconfigure.security.properties.code;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 短信验证码
 * Created by 王大宸 on 2022-09-23 9:57
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.security.code.sms")
public class SecuritySmsCodeProperties extends ValidateCode {


}
