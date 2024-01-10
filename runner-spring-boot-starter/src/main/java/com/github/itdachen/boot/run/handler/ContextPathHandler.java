package com.github.itdachen.boot.run.handler;

import com.github.itdachen.framework.spring.SpringBeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

/**
 * Description: 获取项目下下文
 * Created by 王大宸 on 2023/03/02 10:40
 * Created with IntelliJ IDEA.
 */
public class ContextPathHandler {
    private static final Environment environment = SpringBeanUtils.getBean("environment");

    public static String contextPath() {
        String contextPath = environment.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(contextPath)) {
            return "";
        }
        return contextPath;
    }

}
