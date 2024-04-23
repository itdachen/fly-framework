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
     * @param currentUserInfo currentUserInfo
     * @param userDetails userDetails
     * @return void
     */
    public static void setCurrentUserInfo(CurrentUserInfo currentUserInfo,
                                          UserInfoDetails userDetails) {
        currentUserInfo.setId(userDetails.getId());
        currentUserInfo.setUsername(userDetails.getUsername());
        currentUserInfo.setTenantId(userDetails.getTenantId());
        currentUserInfo.setSignMethod(userDetails.getSignMethod());
        currentUserInfo.setNickName(userDetails.getNickName());
        currentUserInfo.setAvatar(userDetails.getAvatar());
        currentUserInfo.setEmail(userDetails.getEmail());
        currentUserInfo.setSex(userDetails.getSex());
        currentUserInfo.setUserType(userDetails.getUserType());
        currentUserInfo.setTelephone(userDetails.getTelephone());
        currentUserInfo.setValidFlag(userDetails.getValidFlag());
        currentUserInfo.setPlatId(userDetails.getPlatId());
        currentUserInfo.setPlatName(userDetails.getPlatName());
        currentUserInfo.setAppId(userDetails.getAppId());
        currentUserInfo.setAppName(userDetails.getAppName());
        currentUserInfo.setAppVersion(userDetails.getAppVersion());
        currentUserInfo.setAppContextPath(userDetails.getAppContextPath());
        currentUserInfo.setRoleId(userDetails.getRoleId());
        currentUserInfo.setRoleFlag(userDetails.getRoleFlag());
        currentUserInfo.setRoleName(userDetails.getRoleName());
        currentUserInfo.setDeptId(userDetails.getDeptId());
        currentUserInfo.setDeptTitle(userDetails.getDeptTitle());
        currentUserInfo.setParentDeptId(userDetails.getParentDeptId());
        currentUserInfo.setDeptLevel(userDetails.getDeptLevel());
        currentUserInfo.setProvId(userDetails.getProvId());
        currentUserInfo.setCityId(userDetails.getCityId());
        currentUserInfo.setCountyId(userDetails.getCountyId());
        currentUserInfo.setHostIp(userDetails.getHostIp());
        currentUserInfo.setHostProv(userDetails.getHostProv());
        currentUserInfo.setHostCity(userDetails.getHostCity());
        currentUserInfo.setHostAddr(userDetails.getHostAddr());
        currentUserInfo.setUserAgent(userDetails.getUserAgent());
        currentUserInfo.setExpTime(userDetails.getExpTime());
    }

}
