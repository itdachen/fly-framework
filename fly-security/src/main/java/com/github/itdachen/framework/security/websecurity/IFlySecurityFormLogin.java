package com.github.itdachen.framework.security.websecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Description: 表单登录
 * Created by 王大宸 on 2023-07-09 12:26
 * Created with IntelliJ IDEA.
 */
public interface IFlySecurityFormLogin {

    void formLogin(HttpSecurity http) throws Exception;

}
