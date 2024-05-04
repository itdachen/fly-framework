package com.github.itdachen.boot.security.context;

import com.github.itdachen.boot.security.user.CurrentUserInfo;
import com.github.itdachen.framework.context.exception.ClientTokenException;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
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
     * 设置登录用户信息
     *
     * @author 王大宸
     * @date 2024/4/4 0:16
     * @param userInfoDetails userInfoDetails
     * @param userDetails userDetails
     * @return void
     */
    public static void setCurrentUserInfo(CurrentUserInfo userInfoDetails, UserInfoDetails userDetails) {
        userInfoDetails.setId(userDetails.getId());
        userInfoDetails.setUsername(userDetails.getUsername());
        userInfoDetails.setLoginMethod(userDetails.getLoginMethod());

        /* 平台/应用信息 */
        userInfoDetails.setPlatId(userDetails.getPlatId());
        userInfoDetails.setPlatName(userDetails.getPlatName());
        userInfoDetails.setAppId(userDetails.getAppId());
        userInfoDetails.setAppName(userDetails.getAppName());
        userInfoDetails.setAppVersion(userDetails.getAppVersion());
        userInfoDetails.setAppContextPath(userDetails.getAppContextPath());

        /* 租户信息 */
        userInfoDetails.setTenantId(userDetails.getTenantId());
        userInfoDetails.setTenantTitle(userDetails.getTenantTitle());

        /* 用户信息 */
        userInfoDetails.setNickName(userDetails.getNickName());
        userInfoDetails.setAvatar(userDetails.getAvatar());
        userInfoDetails.setEmail(userDetails.getEmail());
        userInfoDetails.setSex(userDetails.getSex());
        userInfoDetails.setUserType(userDetails.getUserType());
        userInfoDetails.setTelephone(userDetails.getTelephone());
        userInfoDetails.setValidFlag(userDetails.getValidFlag());

        /* 身份信息 */
        userInfoDetails.setRoleId(userDetails.getRoleId());
        userInfoDetails.setRoleName(userDetails.getRoleName());
        userInfoDetails.setRoleFlag(userDetails.getRoleFlag());

        /* 区域信息 */
        userInfoDetails.setProvId(userDetails.getProvId());
        userInfoDetails.setCityId(userDetails.getCityId());
        userInfoDetails.setCountyId(userDetails.getCountyId());

        /* 部门信息 */
        userInfoDetails.setDeptId(userDetails.getDeptId());
        userInfoDetails.setDeptTitle(userDetails.getDeptTitle());
        userInfoDetails.setDeptType(userDetails.getDeptType());
        userInfoDetails.setDeptLevel(userDetails.getDeptLevel());
        userInfoDetails.setDeptParentId(userDetails.getDeptParentId());

        /* 操作主机信息 */
        userInfoDetails.setHostIp(userDetails.getHostIp());
        userInfoDetails.setHostOs(userDetails.getHostOs());
        userInfoDetails.setHostBrowser(userDetails.getHostBrowser());
        userInfoDetails.setHostAddr(userDetails.getHostAddr());
        userInfoDetails.setHostUserAgent(userDetails.getHostUserAgent());
        userInfoDetails.setHostProv(userDetails.getHostProv());
        userInfoDetails.setHostCity(userDetails.getHostCity());

        /* 密码更新时间 */
        userInfoDetails.setExpTime(userDetails.getExpTime());
        userInfoDetails.setLastTime(userDetails.getLastTime());
    }

}
