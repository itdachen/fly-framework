package com.github.itdachen.boot.security.validate.code.filter;

import com.github.itdachen.boot.security.validate.code.processor.ValidateCodeProcessorHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * 验证码生成方式
 *
 * @author 王大宸
 * @date 2024-11-28 15:35
 */
public abstract class AbstractValidateCodeGeneratingFilter extends GenericFilterBean {

    /**
     * 访问地址
     */
    private final String validateCodeUrl;
    private final String validateCodeType;
    private final ValidateCodeProcessorHolder validateCodeProcessorHolder;

    public AbstractValidateCodeGeneratingFilter(String validateCodeUrl, String validateCodeType, ValidateCodeProcessorHolder validateCodeProcessorHolder) {
        this.validateCodeUrl = validateCodeUrl;
        this.validateCodeType = validateCodeType;
        this.validateCodeProcessorHolder = validateCodeProcessorHolder;
    }

    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!this.isValidateCodeUrlRequest(request)) {
            chain.doFilter(request, response);
        } else {

            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(this.validateCodeType)
                        .create(new ServletWebRequest(request, response));
            } catch (Exception e) {
//                String type = "图形";
//                if ("sms".equals(this.validateCodeType)) {
//                    type = "短信";
//                }

                String type = "sms".equals(this.validateCodeType) ? "短信" : "图形";
                throw new RuntimeException("获取" + type + "验证码失败！");
            }

        }
    }


    /***
     * 地址校验
     *
     * @author 王大宸
     * @date 2024/11/28 15:38
     * @param request request
     * @return boolean
     */
    protected boolean isValidateCodeUrlRequest(HttpServletRequest request) {
        return this.matches(request, this.validateCodeUrl);
    }

    /***
     * 请求路径校验
     *
     * @author 王大宸
     * @date 2024/11/28 15:39
     * @param request request
     * @param url url
     * @return boolean
     */
    private boolean matches(HttpServletRequest request, String url) {
        if ("GET".equals(request.getMethod()) && url != null) {
            String uri = request.getRequestURI();
            int pathParamIndex = uri.indexOf(59);
            if (pathParamIndex > 0) {
                uri = uri.substring(0, pathParamIndex);
            }

            if (request.getQueryString() != null) {
                uri = uri + "?" + request.getQueryString();
            }

            if ("".equals(request.getContextPath())) {
                return uri.equals(url);
            } else {
                String var10001 = request.getContextPath();

                String[] split = uri.split("\\?");

                return split[0].equals(var10001 + url);
            }
        } else {
            return false;
        }
    }


}
