package com.github.itdachen.framework.security.websecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Description:
 * Created by 王大宸 on 2023-07-09 12:29
 * Created with IntelliJ IDEA.
 */
public interface IFlySecurityRememberMe {

    void rememberMe(HttpSecurity http) throws Exception;

}
