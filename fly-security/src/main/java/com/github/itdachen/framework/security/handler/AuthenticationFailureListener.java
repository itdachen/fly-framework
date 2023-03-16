package com.github.itdachen.framework.security.handler;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * Description: 登录失败监听
 * Created by 王大宸 on 2022-11-16 17:11
 * Created with IntelliJ IDEA.
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        // 在这里记录登陆失败的次数
        String username = e.getAuthentication().getPrincipal().toString();
        System.err.println("登录失败: " + username);
    }

}
