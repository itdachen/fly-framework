package com.github.itdachen.framework.security.session.strategy;

import org.springframework.web.context.request.RequestAttributes;

/**
 * Description: session 管理工具类, 来自于 spring-social-web jar
 * social: SpringSecurity 第三方登录(微信,QQ,微博等)解决方案,已经不更新了
 * Created by 王大宸 on 2022-09-25 10:54
 * Created with IntelliJ IDEA.
 */
public class HttpSessionSessionStrategy {

    private static final int SCOPE = 1;

    public void setAttribute(RequestAttributes request, String name, Object value) {
        request.setAttribute(name, value, SCOPE);
    }

    public Object getAttribute(RequestAttributes request, String name) {
        return request.getAttribute(name, SCOPE);
    }

    public void removeAttribute(RequestAttributes request, String name) {
        request.removeAttribute(name, SCOPE);
    }

}
