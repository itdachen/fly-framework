package com.github.itdachen.boot.security.authentication.platforms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 认证 token , 每个都必须要
 *
 * @author 王大宸
 * @date 2023-11-13 16:04
 */
public class PlatformAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 认证标识 ticket_code
     */
    private final Object principal;

    /**
     * 认证信息获取地址 ticket_uri
     */
    private Object credentials;

    /***
     * 构造函数
     *
     * @author 王大宸
     * @date 2023/1/12 10:40
     * @param principal 登录账号
     * @return
     */
    public PlatformAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    /**
     * This constructor should only be used by <code>AuthenticationManager</code> or
     * <code>AuthenticationProvider</code> implementations that are satisfied with
     * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * authentication token.
     * <p>
     * 认证成果之后
     *
     * @param principal
     * @param authorities
     */
    public PlatformAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // must use super, as we override
    }

    public Object getCredentials() {
        return credentials;
    }

    // ~ Methods
    // ========================================================================================================
    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "JinSanAuthenticationToken: Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

}
