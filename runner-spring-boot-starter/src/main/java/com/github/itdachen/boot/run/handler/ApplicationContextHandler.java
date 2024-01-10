package com.github.itdachen.boot.run.handler;

import org.springframework.context.ApplicationContext;

/**
 * Description: 获取上下文
 * Created by 王大宸 on 2023/03/02 15:22
 * Created with IntelliJ IDEA.
 */
public class ApplicationContextHandler {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextHandler.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


}
