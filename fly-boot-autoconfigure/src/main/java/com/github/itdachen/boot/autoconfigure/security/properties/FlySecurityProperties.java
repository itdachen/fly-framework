package com.github.itdachen.boot.autoconfigure.security.properties;

import com.github.itdachen.boot.autoconfigure.security.constants.SecurityConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 基于 Spring Security 配置
 * Created by 王大宸 on 2023-07-06 15:30
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.security")
public class FlySecurityProperties {

    /* 登录页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上 */
    private String signInPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    /* 退出登录地址 */
    private String logout = "/logout";

    /*
     * 默认注册页配置
     * 第一次使用第三方登录跳转到注册页面
     * 两种情况: 一是一个全新的用户; 二是有自己的用户名和密码,需要绑定
     */
    private String signUpUrl = "/signUp.html";

    /*
     *  退出成功之后跳转的地址
     */
    private String signOutUrl = "/login";

    /*
     * 登录成功之后,默认跳转地址
     */
    private String successForwardUrl = "/";

    /*
     * 不拦截路径, 多个之间用逗号(',')隔开
     */
    private List<String> matchers = new ArrayList<>();

    public String getSignInPage() {
        return signInPage;
    }

    public void setSignInPage(String signInPage) {
        this.signInPage = signInPage;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    public String getSuccessForwardUrl() {
        return successForwardUrl;
    }

    public void setSuccessForwardUrl(String successForwardUrl) {
        this.successForwardUrl = successForwardUrl;
    }

    public List<String> getMatchers() {
        return matchers;
    }

    public void setMatchers(List<String> matchers) {
        this.matchers = matchers;
    }

}
