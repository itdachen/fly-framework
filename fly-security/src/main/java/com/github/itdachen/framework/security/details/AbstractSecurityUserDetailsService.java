package com.github.itdachen.framework.security.details;

import com.github.itdachen.framework.context.permission.PermissionInfo;
import com.github.itdachen.framework.core.constants.UserStatusConstant;
import com.github.itdachen.framework.core.permission.LoginUserModel;
import com.github.itdachen.framework.security.user.CurrentUserInfo;
import com.github.itdachen.framework.security.exception.BizSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    protected PasswordEncoder passwordEncoder;

    /***
     * 根据登录账号查询用户信息
     *
     * @author 王大宸
     * @date 2021/11/27 11:35
     * @param username 登录账号
     * @return com.itdachen.security.core.model.CurrentUser
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
     * @return com.itdachen.security.core.model.CurrentUser
     */
    public CurrentUserInfo loadUserByMobile(String mobile) throws UsernameNotFoundException {
        logger.info("当前登录手机号: " + mobile);
        logger.info("请实现根据手机号查询用户信息...");
        return null;
    }

    /***
     * 根据小程序 code 查询用户信息
     *
     * @author 王大宸
     * @date 2021/11/27 11:36
     * @param appCode        小程序登录获取的 code
     * @param providerId     小程序提供商id
     * @return com.itdachen.security.core.model.CurrentUser
     */
    public CurrentUserInfo appletCodeUser(String appCode, String providerId) throws BizSecurityException {
        logger.info("当前登录appCode: " + appCode + "  当前登录providerId: " + providerId);
        logger.info("根据登录账号密码绑定用户信息...");
        return null;
    }

    /***
     * 根据openId查询用户
     *
     * @author 王大宸
     * @date 2021/11/27 11:37
     * @param openId       用户openId
     * @param providerId   提供商id
     * @return com.itdachen.security.core.model.CurrentUser
     */
    public CurrentUserInfo loadUserByOpenId(String openId, String providerId) throws BizSecurityException {
        logger.info("当前登录openId: " + openId + "  当前登录providerId: " + providerId);
        logger.info("请实现根据openId查询用户...");
        return null;
    }

    /***
     * 根据登录账号密码绑定用户信息
     *
     * @author 王大宸
     * @date 2021/11/27 11:37
     * @param openId       用户openId
     * @param providerId   openId 提供商
     * @param username     登录账号
     * @param password     登录密码
     * @return com.itdachen.security.core.model.CurrentUser
     */
    public CurrentUserInfo bindUserByOpenId(String openId, String providerId, String username, String password) throws BizSecurityException {
        logger.info("当前登录openId: " + openId + "  当前登录providerId: " + providerId);
        logger.info("当前登录username: " + username + "  当前登录password: " + password);
        logger.info("根据登录账号密码绑定用户信息...");
        return null;
    }

    /***
     * 授权
     *
     * @author 王大宸
     * @date 2021/11/27 11:39
     * @param user                  SysUserModel
     * @param userPermission        用户权限
     * @return com.itdachen.security.core.model.CurrentUser
     */
    protected CurrentUserInfo setUserPermission(LoginUserModel user,
                                                Set<PermissionInfo> userPermission) {
        boolean enabled = isEnabled();
        boolean accountNonExpired = accountNonExpired();
        boolean credentialsNonExpired = credentialsNonExpired();
        boolean accountNonLocked = accountNonLocked(user.getStatus());

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // 后端校验权限
        Set<String> perms = new HashSet<>();

        /* 如果账户被冻结或者不可用 */
        if (!enabled || !accountNonExpired
                || !credentialsNonExpired || !accountNonLocked) {
            return currentUser(user, enabled, accountNonExpired,
                    credentialsNonExpired, accountNonLocked, userPermission, perms, grantedAuthorities);
        }

        // 前端标签权限
        StringBuffer sb = new StringBuffer();
        sb.append("ROLE_USER").append(",");
        for (PermissionInfo permission : userPermission) {
            perms.add(permission.getPermission());
            sb.append(permission.getPermission()).append(",");
        }
        grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(sb.toString());

        return currentUser(user,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                userPermission,
                perms,
                grantedAuthorities
        );
    }

    /***
     * 用户登录返回用户信息
     *
     * @author 王大宸
     * @date 2021/11/27 11:39
     * @param user                    用户信息
     * @param userPermission          用户前线
     * @param perms                   权限
     * @param enabled                 账号是否可用
     * @param accountNonExpired       账户没有过期
     * @param credentialsNonExpired   密码没过期
     * @param accountNonLocked        账户没被冻结
     * @param grantedAuthorities      权限
     * @return com.itdachen.security.core.model.CurrentUser
     */
    protected CurrentUserInfo currentUser(LoginUserModel user,
                                          boolean enabled,
                                          boolean accountNonExpired,
                                          boolean credentialsNonExpired,
                                          boolean accountNonLocked,
                                          Set<PermissionInfo> userPermission,
                                          Set<String> perms,
                                          List<GrantedAuthority> grantedAuthorities) {
        return new CurrentUserInfo.Builder()
                .accountNonExpired(accountNonExpired)
                .accountNonLocked(accountNonLocked)
                .credentialsNonExpired(credentialsNonExpired)
                .enabled(enabled)
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .sex(user.getSex())
                .providerId(user.getProviderId())
                .openId(user.getOpenId())
                .avatar(user.getAvatar())
                .departId(user.getDepartId())
                .type(user.getType())
                .isSuperAdmin(user.getType())
                .tenantId(user.getTenantId())
                .grade(user.getGrade())
                .phone(user.getTelephone())
                .tenantId(user.getTenantId())
                .authorities(grantedAuthorities)
                .urls(userPermission)
                .perms(perms)
                .build();
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
