package com.github.itdachen.framework.boot.security.authentication.platforms;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.framework.boot.security.authentication.VerifyTicketToken;
import com.github.itdachen.framework.boot.security.details.AbstractSecurityUserDetailsService;
import com.github.itdachen.framework.boot.security.exception.BizSecurityException;
import com.github.itdachen.framework.boot.security.user.CurrentUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

/**
 * 认证处理器
 *
 * @author 剑鸣秋朔
 * @date 2023-11-13 16:05
 */
public class PlatformAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(PlatformAuthenticationProvider.class);
    private final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private AbstractSecurityUserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    public PlatformAuthenticationProvider(AbstractSecurityUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.authentication.AuthenticationProvider#
     * authenticate(org.springframework.security.core.Authentication)
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PlatformAuthenticationToken token = (PlatformAuthenticationToken) authentication;

        final Object ticketCode = String.valueOf(token.getPrincipal());
        final String ticketUri = String.valueOf(token.getCredentials()) + "?code=" + ticketCode;

        /* 发起回调, 获取用户信息 */
        String body = HttpUtil.createGet(ticketUri).execute().body();

        JSONObject json = JSON.parseObject(body);
        if (200 != json.getInteger("status")) {
            logger.error("获取认证票据失败: {}", json.getString("msg"));
            throw new BizSecurityException("取到认证信息失败!!!");
        }
        JSONObject data = json.getJSONObject("data");
        if (null == data) {
            logger.error("获取认证票据失败: 未获取到认证信息");
            throw new BizSecurityException("未获取到认证信息!!!");
        }
        VerifyTicketToken ticketToken = data.toJavaObject(VerifyTicketToken.class);

        /* 判断过期时间, 过期的票据将无法使用 */
        if (ticketToken.getExpTime().isBefore(LocalDateTime.now())) {
            throw new BizSecurityException("认证票据已过期, 请重新获取认证票据!");
        }

        /* 获取认证信息, 完成认证 */
        CurrentUserInfo currentUser = userDetailsService.findUserDetailsByTicketToken(ticketToken);
        if (null == currentUser) {
            throw new BadCredentialsException(this.messages.getMessage("PlatformAuthenticationProvider.badCredentials", "登录账号不存在"));
        }

        PlatformAuthenticationToken authenticationResult = new PlatformAuthenticationToken(
                currentUser,
                currentUser.getAuthorities()
        );
        authenticationResult.setDetails(token.getDetails());
        return authenticationResult;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.authentication.AuthenticationProvider#
     * supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return PlatformAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public AbstractSecurityUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(AbstractSecurityUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
