package com.github.itdachen.framework.security.websecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Description: Session 管理器
 * Created by 王大宸 on 2023-07-09 12:56
 * Created with IntelliJ IDEA.
 */
public interface IFlySecuritySessionManagement {

    void sessionManagement(HttpSecurity http) throws Exception;

}
