package com.github.itdachen.framework.boot.security.details;

import com.github.itdachen.framework.boot.autoconfigure.app.AppInfoProperties;
import com.github.itdachen.framework.boot.autoconfigure.app.PlatformInfoProperties;
import com.github.itdachen.framework.boot.security.authentication.VerifyTicketToken;
import com.github.itdachen.framework.boot.security.context.SecurityContextHandler;
import com.github.itdachen.framework.boot.security.exception.BizSecurityException;
import com.github.itdachen.framework.boot.security.user.CurrentUserInfo;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;
import com.github.itdachen.framework.core.constants.UserStatusConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * Created by 王大宸 on 2022-09-23 15:34
 * Created with IntelliJ IDEA.
 */
public abstract class AbstractSecurityUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractSecurityUserDetailsService.class);

    private final PlatformInfoProperties platformInfoProperties;
    private final AppInfoProperties appInfoProperties;

    public AbstractSecurityUserDetailsService(PlatformInfoProperties platformInfoProperties,
                                              AppInfoProperties appInfoProperties) {
        this.platformInfoProperties = platformInfoProperties;
        this.appInfoProperties = appInfoProperties;
    }

    /***
     * 根据手机号码查询用户信息
     *
     * @author 王大宸
     * @date 2021/11/27 11:35
     * @param mobile 手机号码
     * @return com.github.itdachen.framework.security.user.CurrentUserInfo
     */
    public CurrentUserInfo loadUserByMobile(String mobile) throws UsernameNotFoundException {
        logger.info("当前登录手机号: " + mobile);
        logger.info("请实现根据手机号查询用户信息...");
        return null;
    }

    /***
     * 根据认证票据, 完成用户信息认证
     *
     * @author 王大宸
     * @date 2023/11/13 16:29
     * @param token  认证票据
     * @return cn.edu.hubu.framework.security.user.CurrentUserInfo
     */
    public CurrentUserInfo findUserDetailsByTicketToken(VerifyTicketToken token) throws UsernameNotFoundException {
        throw new BizSecurityException("登录方法未实现!!!");
    }


    /***
     * 校验用户信息
     *
     * @author 王大宸
     * @date 2024/4/29 22:52
     * @param userInfoDetails userInfoDetails
     * @return com.github.itdachen.boot.security.user.CurrentUserInfo
     */
    protected CurrentUserInfo toVerifyUserDetails(UserInfoDetails userInfoDetails) {
        /* 设置平台,应用信息 */
        setAppInfo(userInfoDetails);

        /* 获取权限 */
        Set<String> authorities = getUserAuthority(userInfoDetails);
        if (null == authorities) {
            authorities = new HashSet<>();
        }

        final boolean enabled = isEnabled();
        final boolean accountNonExpired = accountNonExpired();
        final boolean credentialsNonExpired = credentialsNonExpired();
        final boolean accountNonLocked = accountNonLocked(userInfoDetails.getValidFlag());

        /* 如果账户被冻结或者不可用 */
        if (!enabled || !accountNonExpired || !credentialsNonExpired || !accountNonLocked) {
            return currentUser(userInfoDetails, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, new ArrayList<>());
        }

        // 前端标签权限
        StringBuffer sb = new StringBuffer();
        sb.append("ROLE_USER").append(",");
        for (String permission : authorities) {
            sb.append(permission).append(",");
        }
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(sb.toString());

        return currentUser(userInfoDetails, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }


    /***
     * 获取用户权限
     *
     * @author 王大宸
     * @date 2024/4/29 22:50
     * @param userInfoDetails userInfoDetails
     * @return void
     */
    protected abstract Set<String> getUserAuthority(UserInfoDetails userInfoDetails);


    /***
     * 设置应用信息
     *
     * @author 王大宸
     * @date 2024/4/29 23:10
     * @param userInfoDetails userInfoDetails
     * @return void
     */
    protected void setAppInfo(UserInfoDetails userInfoDetails) {
        userInfoDetails.setPlatId(platformInfoProperties.getId());
        userInfoDetails.setPlatName(platformInfoProperties.getTitle());
        userInfoDetails.setAppId(appInfoProperties.getAppId());
        userInfoDetails.setAppName(appInfoProperties.getAppName());
        userInfoDetails.setAppVersion(appInfoProperties.getVersion());
        userInfoDetails.setAppContextPath(appInfoProperties.getContextPath());
    }

    /***
     * 用户登录返回用户信息
     *
     * @author 王大宸
     * @date 2021/11/27 11:39
     * @param user                    用户信息
     * @param enabled                 账号是否可用
     * @param accountNonExpired       账户没有过期
     * @param credentialsNonExpired   密码没过期
     * @param accountNonLocked        账户没被冻结
     * @param grantedAuthorities      权限
     * @return com.github.itdachen.framework.security.user.CurrentUserInfo
     */
    protected CurrentUserInfo currentUser(UserInfoDetails user,
                                        boolean enabled,
                                        boolean accountNonExpired,
                                        boolean credentialsNonExpired,
                                        boolean accountNonLocked,
                                        List<GrantedAuthority> grantedAuthorities) {
        CurrentUserInfo info = new CurrentUserInfo(user.getUsername(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                grantedAuthorities);

        SecurityContextHandler.setCurrentUserInfo(info, user);
        return info;
    }

    /**
     * 账号是否可用
     */
    protected boolean isEnabled() {
        return true;
    }

    /***
     * 账户没有过期
     */
    protected boolean accountNonExpired() {
        return true;
    }

    /**
     * 密码没过期
     */
    protected boolean credentialsNonExpired() {
        return true;
    }

    /**
     * 账户没被冻结
     */
    protected boolean accountNonLocked(String locked) {
        return UserStatusConstant.Y.equals(locked);
    }

}
