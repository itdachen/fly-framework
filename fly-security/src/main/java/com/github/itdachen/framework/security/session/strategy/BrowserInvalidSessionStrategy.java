package com.github.itdachen.framework.security.session.strategy;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 无效 session 处理
 * Created by 王大宸 on 2021-11-27 11:17
 * Created with IntelliJ IDEA.
 */
public class BrowserInvalidSessionStrategy extends AbstractBrowserSessionStrategy implements InvalidSessionStrategy {

    public BrowserInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request,
                                         HttpServletResponse response) throws IOException, ServletException {
        onSessionInvalid(request, response);
    }
}
