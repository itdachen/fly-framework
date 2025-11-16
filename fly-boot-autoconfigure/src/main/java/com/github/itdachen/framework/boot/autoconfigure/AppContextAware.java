package com.github.itdachen.framework.boot.autoconfigure;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * AppContextAware
 *
 * @author 剑鸣秋朔
 * @date 2024/5/22 22:46
 */
@Component
public class AppContextAware implements ApplicationContextAware {
    private static ApplicationContext ctx = null;

    public AppContextAware() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppContextAware.ctx = applicationContext;
    }

    public static ApplicationContext getInstance() {
        return ctx;
    }

}
