package com.github.itdachen.boot.security.handler;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;

/**
 * Description: 登录失败监听
 * Created by 王大宸 on 2022-11-16 17:11
 * Created with IntelliJ IDEA.
 */
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        String username = e.getAuthentication().getPrincipal().toString();
        Authentication authentication = e.getAuthentication();
        Object credentials = e.getAuthentication().getCredentials();
        Object principal = e.getAuthentication().getPrincipal();
        System.err.println("登录失败: " + username);

      //  System.err.println("登录失败: " + userInfo.toString());

        // 在这里记录登陆失败的次数
       // authFailureBadCredentialsService.onApplicationEvent(e);
    }

}
