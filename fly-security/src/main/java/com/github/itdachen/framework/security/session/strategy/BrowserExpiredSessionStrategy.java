package com.github.itdachen.framework.security.session.strategy;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Description: session 过期处理
 * Created by 王大宸 on 2021-11-27 11:17
 * Created with IntelliJ IDEA.
 */
public class BrowserExpiredSessionStrategy extends AbstractBrowserSessionStrategy implements SessionInformationExpiredStrategy {

    public BrowserExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
