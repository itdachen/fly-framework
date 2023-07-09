package com.github.itdachen.framework.security.websecurity.management;

import com.github.itdachen.framework.security.matchers.IFilterMatchers;
import com.github.itdachen.framework.security.websecurity.IFlySecurityAuthorizeHttpRequests;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

/**
 * Description: 认证路径处理
 * Created by 王大宸 on 2023-07-09 12:59
 * Created with IntelliJ IDEA.
 */
@Component
public class FlySecurityAuthorizeHttpRequests implements IFlySecurityAuthorizeHttpRequests {

    /* 不需要认证的路径 */
    private final IFilterMatchers filterMatchers;

    public FlySecurityAuthorizeHttpRequests(IFilterMatchers filterMatchers) {
        this.filterMatchers = filterMatchers;
    }

    @Override
    public void authorizeHttpRequests(HttpSecurity http) throws Exception {
        /* 授权请求控制 */
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(filterMatchers.matchers())
                .permitAll()
                .anyRequest().authenticated());
    }

}
