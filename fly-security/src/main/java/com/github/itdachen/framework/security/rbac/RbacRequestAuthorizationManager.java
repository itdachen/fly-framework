package com.github.itdachen.framework.security.rbac;


import com.github.itdachen.framework.context.permission.PermissionInfo;
import com.github.itdachen.framework.security.user.CurrentUserInfo;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/***
 * 自定义 Rbac 鉴权
 * SpringSecurity 6.x 默认的注解鉴权不生效, 这里通过 Rbac 模式自定义鉴权
 * @author 王大宸
 * @date 2023/11/27 20:18
 */
@Component
public class RbacRequestAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private static final Logger logger = LoggerFactory.getLogger(RbacRequestAuthorizationManager.class);

    /* 检查是否需要鉴权 */
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 需要认证的权限
     */
    private static List<PermissionInfo> permissionsSet = null;

    private final IRequestAuthorizationHandler authenticationAuthorityMapper;

    public RbacRequestAuthorizationManager(IRequestAuthorizationHandler authorizationHandler) {
        this.authenticationAuthorityMapper = authorizationHandler;
    }


    @PostConstruct
    public void init() {
        logger.info("Initializing application permissions ...");
        // 从数据库加载
        permissionsSet = authenticationAuthorityMapper.init();
    }

    public void clear() {
        permissionsSet.clear();
        permissionsSet = null;
    }

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        boolean granted = isGranted(authentication.get(), requestAuthorizationContext.getRequest());
        // 返回授权决策对象，根据权限结果
        return new AuthorizationDecision(granted);
    }

    private boolean isGranted(Authentication authentication, HttpServletRequest request) {
        return null != authentication && isAuthorized(authentication, request);
    }


    private boolean isAuthorized(Authentication authentication, HttpServletRequest request) {
        try {
            // 自定义的权限控制，request 可以获取到当前的请求信息。
            // authentication 就是我们的认证对象，我们可以直接拿到认证用户的权限
            Object principal = authentication.getPrincipal();
            if (null == principal || "anonymousUser".equals(principal)) {
                return false;
            }

            if (null == permissionsSet || permissionsSet.isEmpty()) {
                return true;
            }


            String uri;
            PermissionInfo hasPermissionInfo = null;
            for (PermissionInfo info : permissionsSet) {
                uri = info.getUri().replaceAll("\\{\\*\\}", "*");
                if (info.getMethod().equals(request.getMethod()) && antPathMatcher.match(uri, request.getRequestURI())) {
                    hasPermissionInfo = info;
                    break;
                }
            }

            // 说明当前访问资源不做权限控制
            if (null == hasPermissionInfo) {
                return true;
            }

            /* 用户鉴权 */
            if (principal instanceof CurrentUserInfo userInfo) {
                Collection<GrantedAuthority> authorities = userInfo.getAuthorities();

                if (null == authorities || authorities.isEmpty()) {
                    return false;
                }


                for (GrantedAuthority info : authorities) {
                    if (hasPermissionInfo.getPermission().equals(info.getAuthority())) {
                        return true;
                    }
                }
                return false;

//                List<PermissionInfo> userInfoPermissions = userInfo.getPermissions();
//                if (null == userInfoPermissions || userInfoPermissions.isEmpty()) {
//                    return false;
//                }
//                for (PermissionInfo info : userInfoPermissions) {
//                    if (hasPermissionInfo.getPermission().equals(info.getPermission())) {
//                        return true;
//                    }
//                }
            }
            return false;
        } catch (Exception ex) {
            logger.error("权限校验失败: ", ex);
            return false;
        }
    }


}
