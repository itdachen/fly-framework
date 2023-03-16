package com.github.itdachen.framework.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Description: 验证码异常
 * Created by 王大宸 on 2022-09-23 14:25
 * Created with IntelliJ IDEA.
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 8595045285189432781L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
