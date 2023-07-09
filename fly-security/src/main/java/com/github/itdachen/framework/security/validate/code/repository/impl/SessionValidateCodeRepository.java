package com.github.itdachen.framework.security.validate.code.repository.impl;

import com.github.itdachen.framework.security.session.strategy.HttpSessionSessionStrategy;
import com.github.itdachen.framework.security.validate.code.ValidateCode;
import com.github.itdachen.framework.security.validate.code.enums.ValidateCodeType;
import com.github.itdachen.framework.security.validate.code.repository.ValidateCodeRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description: 浏览器验证码处理器
 * Created by 王大宸 on 2022-10-16 14:03
 * Created with IntelliJ IDEA.
 */
@Component
public class SessionValidateCodeRepository  implements ValidateCodeRepository {
    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    /**
     * 操作session的工具类
     */
    private final HttpSessionSessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request, getSessionKey(request, validateCodeType), code);
    }

    /***
     * 构建验证码放入session时的key
     *
     * @author 王大宸
     * @date 2021/11/27 11:19
     * @param request
     * @param validateCodeType
     * @return java.lang.String
     */
    private String getSessionKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(request, validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(request, codeType));
    }

}
