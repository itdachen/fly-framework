package com.github.itdachen.framework.autoconfigure.security.properties.session;

import com.github.itdachen.framework.autoconfigure.security.constants.SecurityConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: Spring Security Session 配置
 * Created by 王大宸 on 2022-09-23 10:03
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.security.session")
public class FlySecuritySessionProperties {

    /**
     * 同一个用户在系统中的最大session数，默认1
     */
    private int maximumSessions = 1;

    /**
     * 达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
     */
    private boolean maxSessionsPreventsLogin = false;

    /**
     * session失效时跳转的地址
     */
    private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;

    public int getMaximumSessions() {
        return maximumSessions;
    }

    public void setMaximumSessions(int maximumSessions) {
        this.maximumSessions = maximumSessions;
    }

    public boolean getMaxSessionsPreventsLogin() {
        return maxSessionsPreventsLogin;
    }

    public void setMaxSessionsPreventsLogin(boolean maxSessionsPreventsLogin) {
        this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
    }

    public String getSessionInvalidUrl() {
        return sessionInvalidUrl;
    }

    public void setSessionInvalidUrl(String sessionInvalidUrl) {
        this.sessionInvalidUrl = sessionInvalidUrl;
    }

}
