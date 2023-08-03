package com.github.itdachen.framework.security.websecurity.management;

import com.github.itdachen.framework.security.matchers.IFilterMatchers;
import com.github.itdachen.framework.security.websecurity.IFlySecurityAuthorizeHttpRequests;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /***
     * 授权请求控制
     *
     * @author 王大宸
     * @date 2023/8/3 23:03
     * @param http http
     * @return void
     */
    @Override
    public void authorizeHttpRequests(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(requestMatcher()).permitAll()
                .anyRequest().authenticated());
    }

    /***
     * SpringSecurity 不拦截的请求路径
     * 狗贼, 新版本不能直接填写访问路径
     *
     * @author 王大宸
     * @date 2023/8/3 23:03
     * @return org.springframework.security.web.util.matcher.AntPathRequestMatcher[]
     */
    private AntPathRequestMatcher[] requestMatcher() {
        List<String> list = filterMatchers.requestMatchers();
        List<AntPathRequestMatcher> requestMatchers = new ArrayList<>();
        for (String uri : list) {
            requestMatchers.add(new AntPathRequestMatcher(uri));
        }
        return requestMatchers.toArray(new AntPathRequestMatcher[0]);
    }


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers(requestMatcher());
//    }


//    private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
//            new AntPathRequestMatcher("/register"),
//            new AntPathRequestMatcher("/api/v1/getUsers"),
//            new AntPathRequestMatcher("/h2-console/**")
//    };



}
