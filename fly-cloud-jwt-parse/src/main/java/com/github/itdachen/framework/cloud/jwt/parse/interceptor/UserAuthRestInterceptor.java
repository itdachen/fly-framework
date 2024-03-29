package com.github.itdachen.framework.cloud.jwt.parse.interceptor;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;
import com.github.itdachen.framework.cloud.jwt.parse.IVerifyTicketTokenHelper;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.annotation.IgnoreUserToken;
import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.exception.BizException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Map;

/**
 * Description: 用户认证拦截器
 * Created by 王大宸 on 2023/05/05 15:19
 * Created with IntelliJ IDEA.
 */
public class UserAuthRestInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(UserAuthRestInterceptor.class);

    private final IVerifyTicketTokenHelper verifyTicketTokenService;

    public UserAuthRestInterceptor(IVerifyTicketTokenHelper verifyTicketTokenService) {
        this.verifyTicketTokenService = verifyTicketTokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
        final HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken ignoreUserToken = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (ignoreUserToken == null) {
            ignoreUserToken = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if (ignoreUserToken != null) {
            return true;
        }

        // 是否鉴权
        String header = request.getHeader(UserInfoConstant.VERIFIED_TICKET);
        boolean passFlag = UserInfoConstant.VERIFIED_TICKET_VALUE.equals(header);
        if (!passFlag) {
            // 是否内部调用
            header = request.getHeader(UserInfoConstant.NAL_FEIGN);
            passFlag = UserInfoConstant.NAL_FEIGN_VALUE.equals(header);
        }
        /* 既没有通过网关鉴权, 也不是内网调用, 返回非法请求 */
        if (!passFlag) {
            writeErrorResponse(response, HttpStatus.OK.value(), HttpStatus.BAD_REQUEST.value(), "非法请求");
            return false;
        }


        String token = request.getHeader(UserInfoConstant.HEADER_AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(UserInfoConstant.HEADER_AUTHORIZATION)) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }
        if (StringUtils.isEmpty(token)) {
            throw new BizException("用户认证失败");
        }
        token = token.replaceAll("%20", " ");
        if (token.startsWith(UserInfoConstant.TOKEN_TYPE)) {
            token = token.substring(UserInfoConstant.TOKEN_TYPE.length());
        }
        try {

            final IJwtInfo ijwtInfo = verifyTicketTokenService.verifyTicketToken(token);
            BizContextHandler.setTokenId(ijwtInfo.getTokenId());
            BizContextHandler.setAccount(ijwtInfo.getUniqueName());
            BizContextHandler.setUserId(ijwtInfo.getUserId());
            BizContextHandler.setNickName(ijwtInfo.getNickName());
            BizContextHandler.setTenantId(ijwtInfo.getTenantId());

            final Map<String, String> otherInfo = ijwtInfo.getOtherInfo();
            BizContextHandler.setClientId(otherInfo.get(UserInfoConstant.CLIENT_ID));
            BizContextHandler.setSignMethod(otherInfo.get(UserInfoConstant.SIGN_METHOD));
            BizContextHandler.setAvatar(otherInfo.get(UserInfoConstant.AVATAR));
            BizContextHandler.setAnId(otherInfo.get(UserInfoConstant.AN_ID));
            BizContextHandler.setAnTitle(otherInfo.get(UserInfoConstant.AN_TITLE));
            BizContextHandler.setAppId(otherInfo.get(UserInfoConstant.APP_ID));
            BizContextHandler.setOpenId(otherInfo.get(UserInfoConstant.OPEN_ID));
            BizContextHandler.setUserType(otherInfo.get(UserInfoConstant.USER_TYPE));
            BizContextHandler.setSex(otherInfo.get(UserInfoConstant.SEX));
            BizContextHandler.setDeptId(otherInfo.get(UserInfoConstant.DEPT_ID));
            BizContextHandler.setDeptTitle(otherInfo.get(UserInfoConstant.DEPT_TITLE));
            BizContextHandler.setTelephone(otherInfo.get(UserInfoConstant.TELEPHONE));
            BizContextHandler.setEmail(otherInfo.get(UserInfoConstant.E_MAIL));
            //  BizContextHandler.setOther(otherInfo.get(UserInfoConstant.OTHER));

            return true;
        } catch (Exception ex) {
            logger.error("用户认证错误：", ex);
            writeErrorResponse(response, HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BizContextHandler.remove();
    }

    private String getMessage(int status, String msg) {
        return "{\"success\":\"false\",\"status\":\"" + status + "\", \"msg\":\"" + msg + "\",\"data\": null}";
    }

    private void writeErrorResponse(HttpServletResponse response, int httpStatus, int status, String msg) throws IOException {
        response.setStatus(httpStatus);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(getMessage(status, msg));
    }


}
