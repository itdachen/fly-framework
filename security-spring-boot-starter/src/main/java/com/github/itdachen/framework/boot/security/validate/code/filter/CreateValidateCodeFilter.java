package com.github.itdachen.framework.boot.security.validate.code.filter;

import com.github.itdachen.framework.boot.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.framework.boot.security.validate.code.processor.ValidateCodeProcessorHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成验证码
 *
 * @author 剑鸣秋朔
 * @date 2024-11-27 17:14
 */
@Deprecated
public class CreateValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 读取配置文件
     */
    private final Environment env;

    /**
     * 系统中的校验码处理器
     */
    private final ValidateCodeProcessorHolder validateCodeProcessorHolder;
    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, String> urlMap = new HashMap<>();

    public CreateValidateCodeFilter(ValidateCodeProcessorHolder validateCodeProcessorHolder,
                                    Environment env) {
        this.validateCodeProcessorHolder = validateCodeProcessorHolder;
        this.env = env;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String type = "";

        try {
            validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
        } catch (Exception e) {
            logger.error("获取验证码失败!");
            return;
        }

        filterChain.doFilter(request, response);

    }


    /**
     * 初始化要拦截的url配置信息
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        final String contextPath = getContextPath();
        /* 图形验证码 */
        urlMap.put("image", contextPath + SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM + "/image");
        /* 短信验证码 */
        urlMap.put("sms", contextPath + SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM + "/sms");
        // addUrlToMap(contextPath, smsCodeProperties.getUrl(), ValidateCodeType.SMS);

    }


    /***
     * 读取上下文, 如果上下文不存在, 则返回空字符串
     *
     * @author 剑鸣秋朔
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
     * @author 剑鸣秋朔
     * @date 2021/11/27 10:25
     * @param urlString
     * @param type
     * @return void
     */
//    protected void addUrlToMap(final String contextPath, String urlString, ValidateCodeType type) {
//        if (StringUtils.isNotBlank(urlString)) {
//            /* 加载当前项目上下文 */
//            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
//            for (String url : urls) {
//                if (StringUtils.isNotEmpty(url) && !url.startsWith(contextPath)) {
//                    url = contextPath + url;
//                }
//                urlMap.put(url, type);
//            }
//        }
//    }

}
