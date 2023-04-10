package com.github.itdachen.framework.security.context;

import com.github.itdachen.framework.security.exception.ClientTokenException;
import com.github.itdachen.framework.security.user.CurrentUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 当前登录信息上下文
 * Created by 王大宸 on 2022-09-23 10:07
 * Created with IntelliJ IDEA.
 */
public class SecurityContextHandler {

    private static final Logger logger = LoggerFactory.getLogger(SecurityContextHandler.class);

    /***
     * 功能说明：获取当前认证信息
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
    /***
     * 动态加载权限
     *
     * @author 王大宸
     * @date 2022/12/8 9:29
     * @param list list
     * @return void
     */
    public static void refreshAuthorities(List<String> list) {
        // 得到当前的认证信息
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //  生成当前的所有授权
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            updatedAuthorities.add(new SimpleGrantedAuthority(list.get(i)));
        }
        // 生成新的认证信息
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        // 重置认证信息
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    /***
     * 功能说明：获取当前登录用户
     *
     * @author 王大宸
     * @date 2021/1/22 14:23
     * @param
     * @return com.home.security.model.CurrentUser
     */
    public static CurrentUserInfo getUserInfo() throws ClientTokenException {
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
        if (principal instanceof CurrentUserInfo) {
            return (CurrentUserInfo) principal;
        }
        if ("anonymousUser".equals(principal)) {
            logger.error("匿名用户...");
            throw new ClientTokenException("获取当前登录用户失败!");
        }
        throw new ClientTokenException("获取当前登录用户失败!");
    }

}
