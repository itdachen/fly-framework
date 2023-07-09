package com.github.itdachen.framework.security.websecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Description: 认证路径
 * Created by 王大宸 on 2023-07-09 12:28
 * Created with IntelliJ IDEA.
 */
public interface IFlySecurityAuthorizeHttpRequests {

    void authorizeHttpRequests(HttpSecurity http) throws Exception;

}
