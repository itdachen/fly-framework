package com.github.itdachen.framework.security.details;

import com.github.itdachen.framework.context.userdetails.CurrentUserDetails;
import com.github.itdachen.framework.core.constants.UserStatusConstant;
import com.github.itdachen.framework.security.user.CurrentUserInfo;
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

    /***
     * 根据登录账号查询用户信息
     *
     * @author 王大宸
     * @date 2021/11/27 11:35
     * @param username 登录账号
     * @return com.github.itdachen.framework.security.user.CurrentUserInfo
     */
    @Override
    public CurrentUserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("当前登录账号: " + username);
        logger.info("请实现根据登录账号查询用户信息");
        return null;
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
     * 授权
     *
     * @author 王大宸
     * @date 2021/11/27 11:39
     * @param user                  user
     * @param authorities           用户权限
     * @return com.github.itdachen.framework.security.user.CurrentUserInfo
     */
    protected CurrentUserInfo setUserPermission(CurrentUserDetails user,
                                                Set<String> authorities) {
        boolean enabled = isEnabled();
        boolean accountNonExpired = accountNonExpired();
        boolean credentialsNonExpired = credentialsNonExpired();
        boolean accountNonLocked = accountNonLocked(user.getStatus());

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        /* 如果账户被冻结或者不可用 */
        if (!enabled || !accountNonExpired
                || !credentialsNonExpired || !accountNonLocked) {
            return currentUser(user, enabled, accountNonExpired,
                    credentialsNonExpired, accountNonLocked, grantedAuthorities);
        }

        // 前端标签权限
        StringBuffer sb = new StringBuffer();
        sb.append("ROLE_USER").append(",");
        for (String permission : authorities) {
            sb.append(permission).append(",");
        }
        grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(sb.toString());

        return currentUser(user,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                grantedAuthorities
        );
    }

    protected CurrentUserInfo setUserPermission(CurrentUserDetails user) {
        return setUserPermission(user, new HashSet<>());
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
    protected CurrentUserInfo currentUser(CurrentUserDetails user,
                                          boolean enabled,
                                          boolean accountNonExpired,
                                          boolean credentialsNonExpired,
                                          boolean accountNonLocked,
                                          List<GrantedAuthority> grantedAuthorities) {
        CurrentUserInfo info = new CurrentUserInfo(
                user.getAccount(),
                user.getAccountSecret(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                grantedAuthorities);

        info.setId(user.getId());
        info.setTenantId(user.getTenantId());
        info.setClientId(user.getClientId());
        info.setSignMethod(user.getSignMethod());
        info.setNickName(user.getNickName());
        info.setAvatar(user.getAvatar());
        info.setTelephone(user.getTelephone());
        info.setEmail(user.getEmail());
        info.setAccount(user.getAccount());
        info.setAccountSecret(user.getAccountSecret());
        info.setAppId(user.getAppId());
        info.setOpenId(user.getOpenId());
        info.setUserType(user.getUserType());
        info.setSex(user.getSex());
        info.setDeptId(user.getDeptId());
        info.setDeptTitle(user.getDeptTitle());
        info.setParentDeptId(user.getParentDeptId());
        info.setDeptLevel(user.getDeptLevel());
        info.setAnId(user.getAnId());
        info.setAnTitle(user.getAnTitle());
        info.setIsSuperAdmin(user.getIsSuperAdmin());
        info.setStatus(user.getStatus());
        info.setExpTime(user.getExpTime());
        info.setOther(user.getOther());
        info.setPermissions(user.getPermissions());
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
        return UserStatusConstant.IS_OK.equals(locked);
    }

}
