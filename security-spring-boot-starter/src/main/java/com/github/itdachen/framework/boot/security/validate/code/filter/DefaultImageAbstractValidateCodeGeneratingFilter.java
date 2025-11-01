package com.github.itdachen.framework.boot.security.validate.code.filter;

import com.github.itdachen.framework.boot.security.validate.code.processor.ValidateCodeProcessorHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 默认图形验证码处理生成
 * 模仿: org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter
 *
 * @author 王大宸
 * @date 2024-11-28 15:32
 */
public class DefaultImageAbstractValidateCodeGeneratingFilter extends AbstractValidateCodeGeneratingFilter {
    public static final String VALIDATE_CODE_URL = "/code/image";
    public static final String VALIDATE_CODE_TYPE = "image";

    public DefaultImageAbstractValidateCodeGeneratingFilter(ValidateCodeProcessorHolder validateCodeProcessorHolder) {
        super(VALIDATE_CODE_URL, VALIDATE_CODE_TYPE, validateCodeProcessorHolder);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }


}
