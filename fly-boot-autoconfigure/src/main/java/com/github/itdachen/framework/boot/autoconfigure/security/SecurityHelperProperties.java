package com.github.itdachen.framework.boot.autoconfigure.security;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.framework.boot.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.boot.autoconfigure.security.properties.code.SecurityImageCodeProperties;
import com.github.itdachen.framework.boot.autoconfigure.security.properties.code.SecuritySmsCodeProperties;
import com.github.itdachen.framework.boot.autoconfigure.security.properties.rememberme.SecurityRememberMeProperties;
import com.github.itdachen.framework.boot.autoconfigure.security.properties.session.SecuritySessionProperties;
import com.github.itdachen.framework.boot.autoconfigure.security.properties.third.SecurityThirdProperties;

/**
 * Spring Security 安全认证配置
 *
 * @author 剑鸣秋朔
 * @date 2024-06-19 16:43
 */
public class SecurityHelperProperties {

    public FlySecurityProperties properties() {
        return AppContextHelper.getBean(FlySecurityProperties.class);
    }

    public SecuritySessionProperties session() {
        return AppContextHelper.getBean(SecuritySessionProperties.class);
    }

    public SecurityRememberMeProperties rememberMe() {
        return AppContextHelper.getBean(SecurityRememberMeProperties.class);
    }

    public SecurityImageCodeProperties imageCode() {
        return AppContextHelper.getBean(SecurityImageCodeProperties.class);
    }

    public SecuritySmsCodeProperties smsCode() {
        return AppContextHelper.getBean(SecuritySmsCodeProperties.class);
    }

    public SecurityThirdProperties third() {
        return AppContextHelper.getBean(SecurityThirdProperties.class);
    }


}
