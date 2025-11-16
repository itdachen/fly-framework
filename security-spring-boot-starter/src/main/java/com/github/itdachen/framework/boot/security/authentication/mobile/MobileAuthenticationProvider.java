package com.github.itdachen.framework.boot.security.authentication.mobile;

import com.github.itdachen.framework.boot.security.details.AbstractSecurityUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Description: 认证处理器
 * Created by 剑鸣秋朔 on 2023-12-09 23:46
 * Created with IntelliJ IDEA.
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private AbstractSecurityUserDetailsService userDetailsService;

    public MobileAuthenticationProvider(AbstractSecurityUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String mobile = authentication.getName();
        // 根据邮箱加载用户信息
        UserDetails userDetails = userDetailsService.loadUserByMobile(mobile);
        if (userDetails == null) {
            // 可以自定义异常类型
            throw new InternalAuthenticationServiceException("用户信息不存在, 请检查电话号码是否正确!");
        }

        MobileAuthenticationToken authenticationResult = new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationResult.setDetails(authentication.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (MobileAuthenticationToken.class.isAssignableFrom(authentication));
    }


    public void setUserDetailsService(AbstractSecurityUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


}
