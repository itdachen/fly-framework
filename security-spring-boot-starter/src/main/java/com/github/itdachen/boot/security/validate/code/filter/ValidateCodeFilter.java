package com.github.itdachen.boot.security.validate.code.filter;

import com.github.itdachen.boot.security.exception.ValidateCodeException;
import com.github.itdachen.boot.security.validate.code.enums.ValidateCodeType;
import com.github.itdachen.boot.security.validate.code.processor.ValidateCodeProcessorHolder;
import com.github.itdachen.boot.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.boot.autoconfigure.security.properties.code.SecurityImageCodeProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.code.SecuritySmsCodeProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description: 验证码过滤器
 * Created by 王大宸 on 2021-11-27 10:25
 * Created with IntelliJ IDEA.
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 读取配置文件
     */
    private final Environment env;

    /**
     * 验证码校验失败处理器
     */
    private final AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 图形验证码认证配置
     */
    private final SecurityImageCodeProperties imageCodeProperties;

    /**
     * 短信验证码认证配置
     */
    private final SecuritySmsCodeProperties smsCodeProperties;

    /**
     * 系统中的校验码处理器
     */
    private final ValidateCodeProcessorHolder validateCodeProcessorHolder;
    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();
    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    public ValidateCodeFilter(AuthenticationFailureHandler authenticationFailureHandler,
                              SecurityImageCodeProperties imageCodeProperties,
                              SecuritySmsCodeProperties smsCodeProperties,
                              ValidateCodeProcessorHolder validateCodeProcessorHolder,
                              Environment env) {
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.imageCodeProperties = imageCodeProperties;
        this.smsCodeProperties = smsCodeProperties;
        this.validateCodeProcessorHolder = validateCodeProcessorHolder;
        this.env = env;
    }

    /**
     * 初始化要拦截的url配置信息
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        final String contextPath = getContextPath();

        /* 图形验证码 */
        urlMap.put(contextPath + SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(contextPath, imageCodeProperties.getUrl(), ValidateCodeType.IMAGE);

        /* 短信验证码 */
        urlMap.put(contextPath + SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(contextPath, smsCodeProperties.getUrl(), ValidateCodeType.SMS);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型: " + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(request, response));
                logger.info("验证码校验通过");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                logger.error("验证码校验未通过");
                return;
            }
        }

        chain.doFilter(request, response);

    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if ("get".equals(request.getMethod()) || "GET".equals(request.getMethod())) {
            return null;
        }
        Set<String> urls = urlMap.keySet();
        for (String url : urls) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                result = urlMap.get(url);
            }
        }
        return result;
    }

    /***
     * 读取上下文, 如果上下文不存在, 则返回空字符串
     *
     * @author 王大宸
     * @date 2023/2/15 9:22
     * @return java.lang.String
     */
    private String getContextPath() {
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(contextPath)) {
            return "";
        }
        return contextPath;
    }


    /***
     * 讲系统中配置的需要校验验证码的URL根据校验的类型放入map
     *
     * @author 王大宸
     * @date 2021/11/27 10:25
     * @param urlString
     * @param type
     * @return void
     */
    protected void addUrlToMap(final String contextPath, String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            /* 加载当前项目上下文 */
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                if (StringUtils.isNotEmpty(url) && !url.startsWith(contextPath)) {
                    url = contextPath + url;
                }
                urlMap.put(url, type);
            }
        }
    }


}
