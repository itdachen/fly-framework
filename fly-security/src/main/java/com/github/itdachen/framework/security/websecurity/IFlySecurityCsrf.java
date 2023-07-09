package com.github.itdachen.framework.security.websecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Description:
 * Created by 王大宸 on 2023-07-09 13:21
 * Created with IntelliJ IDEA.
 */
public interface IFlySecurityCsrf {

   void csrf(HttpSecurity http) throws Exception;

}
