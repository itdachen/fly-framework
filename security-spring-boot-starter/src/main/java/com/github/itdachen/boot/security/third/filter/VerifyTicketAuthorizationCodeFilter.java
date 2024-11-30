package com.github.itdachen.boot.security.third.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.boot.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.boot.security.authentication.VerifyTicketToken;
import com.github.itdachen.boot.security.third.service.IThirdPlatformVerifyTicketTokenService;
import com.github.itdachen.framework.core.response.ServerResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * Description: 校验授权码
 * 测试: http://127.0.0.1:7080/fly/verify/ticket/token/authorize?code=1643160334707593216
 *
 * @author 王大宸
 * @date 2024/11/30 23:25
 */
public class VerifyTicketAuthorizationCodeFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(VerifyTicketAuthorizationCodeFilter.class);
    private static final String HTTP_URI = SecurityConstants.VERIFY_TICKET_TOKEN;

//    private final IThirdPlatformVerifyTicketTokenService thirdVerifyTicketTokenService;
//
//    public VerifyTicketAuthorizationCodeFilter(IThirdPlatformVerifyTicketTokenService thirdVerifyTicketTokenService) {
//        this.thirdVerifyTicketTokenService = thirdVerifyTicketTokenService;
//    }

    @Autowired
    private IThirdPlatformVerifyTicketTokenService thirdVerifyTicketTokenService;


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (!isUrlRequest(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            this.doFilter(request, response);
        }

    }


    /***
     * 过滤器逻辑处理
     *
     * @author 王大宸
     * @date 2024/11/30 23:42
     * @param request request
     * @param response response
     * @return void
     */
    private void doFilter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String code = request.getParameter("code");
            VerifyTicketToken tokenVo = thirdVerifyTicketTokenService.findVerifyTicketToken(code);

            String jsonString = JSONObject.toJSONString(tokenVo);

            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            String body = ServerResponse.getOKBody(200, "获取用户票据信息成功！", jsonString);

            response.getWriter().write(body);

        } catch (Exception e) {
            logger.error("第三方平台用户票据认证失败: {}", e.getMessage());
            response.setStatus(500);
            response.setContentType("application/json;charset=utf-8");
            String body = ServerResponse.getOKBody(500, "获取用户票据失败！", null);

            response.getWriter().write(body);

            // return ServerResponse.err("系统错误, 请联系管理人员!");
        }
    }


    private boolean isUrlRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        final String contextPath = request.getContextPath();
        if ("".equals(contextPath)) {
            return HTTP_URI.equals(uri);
        } else {
            String[] split = uri.split("\\?");
            return split[0].equals(contextPath + HTTP_URI);
        }
    }


}
