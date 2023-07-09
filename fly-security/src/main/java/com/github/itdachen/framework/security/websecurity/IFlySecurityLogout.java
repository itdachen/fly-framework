package com.github.itdachen.framework.security.websecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Description: 退出登录
 * Created by 王大宸 on 2023-07-09 12:53
 * Created with IntelliJ IDEA.
 */
public interface IFlySecurityLogout {

    void logout(HttpSecurity http) throws Exception;

}
