package com.github.itdachen.boot.security.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.itdachen.framework.core.response.ServerResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Description:
 * Created by 王大宸 on 2021-11-27 11:14
 * Created with IntelliJ IDEA.
 */
public class AbstractBrowserSessionStrategy {
    private static final Logger logger = LoggerFactory.getLogger(AbstractBrowserSessionStrategy.class);

    /**
     * 跳转的url
     */
    private String destinationUrl;
    /**
     * 跳转前是否创建新的session
     */
    private boolean createNewSession = true;

    /**
     * 重定向策略
     */
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param invalidSessionUrl
     */
    public AbstractBrowserSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    /***
     * session 过期处理
     *
     * @author 王大宸
     * @date 2021/11/27 11:16
     * @param request
     * @param response
     * @return void
     */
    protected void onSessionInvalid(HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {

        if (createNewSession) {
            request.getSession();
        }

        redirectStrategy.sendRedirect(request, response, destinationUrl);

//        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
//            targetUrl = destinationUrl + ".html";
//            logger.info("session失效,跳转到" + targetUrl);
//            redirectStrategy.sendRedirect(request, response, targetUrl);
//        } else {
//            Object result = buildResponseContent(request);
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(result));
//        }

    }

    /**
     * @param request
     * @return
     */
    protected Object buildResponseContent(HttpServletRequest request) {
        String message = "session已失效";
        if (isConcurrency()) {
            message = message + "，有可能是并发登录导致的";
        }
        return ServerResponse.errMsg(message);
    }

    /**
     * session失效是否是并发导致的
     *
     * @return
     */
    protected boolean isConcurrency() {
        return false;
    }

    /**
     * Determines whether a new session should be created before redirecting (to
     * avoid possible looping issues where the same session ID is sent with the
     * redirected request). Alternatively, ensure that the configured URL does
     * not pass through the {@code SessionManagementFilter}.
     *
     * @param createNewSession defaults to {@code true}.
     */
    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }


}
