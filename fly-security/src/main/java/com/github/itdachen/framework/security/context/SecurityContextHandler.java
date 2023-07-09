package com.github.itdachen.framework.security.context;

import com.github.itdachen.framework.security.exception.ClientTokenException;
import com.github.itdachen.framework.security.user.CurrentUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Description: 当前登录信息上下文
 * Created by 王大宸 on 2022-09-23 10:07
 * Created with IntelliJ IDEA.
 */
public class SecurityContextHandler {
    private static final Logger logger = LoggerFactory.getLogger(SecurityContextHandler.class);

    /***
     * 获取当前认证信息
     *
     * @author 王大宸
     * @date 2021/1/22 14:25
     * @param
     * @return org.springframework.security.core.Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /***
     * 动态加载权限
     *
     * @author 王大宸
     * @date 2022/12/8 9:29
     * @param list list
     * @return void
     */
    public static void refreshAuthorities(List<String> list) {
        final SecurityContext context = SecurityContextHolder.getContext();
        // 得到当前的认证信息
        final Authentication auth = context.getAuthentication();
        //  生成当前的所有授权
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
        for (String s : list) {
            updatedAuthorities.add(new SimpleGrantedAuthority(s));
        }
        // 生成新的认证信息
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        // 重置认证信息
        context.setAuthentication(newAuth);
    }

    /***
     * 获取当前登录用户
     *
     * @author 王大宸
     * @date 2021/1/22 14:23
     * @param
     * @return com.home.security.model.CurrentUser
     */
    public static Object getUserInfo() throws ClientTokenException {
        SecurityContext context = SecurityContextHolder.getContext();
        if (null == context) {
            throw new ClientTokenException("用户未登录!");
        }
        Authentication authentication = context.getAuthentication();
        if (null == authentication) {
            throw new ClientTokenException("用户未登录!");
        }
        Object principal = authentication.getPrincipal();
        if (null == principal) {
            throw new ClientTokenException("用户未登录!");
        }
        if ("anonymousUser".equals(principal)) {
            logger.error("匿名用户...");
            throw new ClientTokenException("获取当前登录用户失败!");
        }
        return principal;
    }

    /***
     * 获取用户权限信息
     *
     * @author 王大宸
     * @date 2023/4/17 21:08
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getUserAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return Collections.singletonList(authorities.toString());
    }


    /***
     * 重新加载用户权限
     *
     * @author 王大宸
     * @date 2023/4/6 15:39
     * @param authorities authorities
     * @return void
     */
    public static void reloadUserAuthority(String... authorities) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        CurrentUserInfo principal = (CurrentUserInfo) authentication.getPrincipal();
        reloadUserAuthority(principal, authorities);
    }

    /***
     * 重新加载用户信息
     *
     * @author 王大宸
     * @date 2023/4/6 14:39
     * @param principal   新增的认证信息, 继承 SpringSecurity User 类
     * @param authorities 权限, 字符串数组
     * @return void
     */
    public static void reloadUserAuthority(CurrentUserInfo principal,
                                           String... authorities) {
        // 新的权限
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(authorities);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        //  authentication.setAuthenticated(authorityList);
        // 重新new一个token，因为Authentication中的权限是不可变的.
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal,
                authentication.getCredentials(), authorityList);
        auth.setDetails(authentication.getDetails());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
