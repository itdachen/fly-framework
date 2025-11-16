package com.github.itdachen.framework.boot.autoconfigure;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 应用上下文
 *
 * @author 剑鸣秋朔
 * @date 2024/5/22 22:44
 */
public class AppContextHelper {
    private static final Environment environment = AppContextHelper.getBean("environment");

    /**
     * Spring Application上下文对象
     *
     * @return org.springframework.context.ApplicationContext
     * @author 剑鸣秋朔
     * @date 2024/5/27 21:30
     */
    public static ApplicationContext context() {
        return AppContextAware.getInstance();
    }

    /***
     * 获取服务上下文
     *
     * @author 剑鸣秋朔
     * @date 2024/12/15 16:35
     * @return java.lang.String
     */
    public static String contextPath() {
        String contextPath = environment.getProperty("server.servlet.context-path");
        if (null == contextPath || "".equals(contextPath)) {
            return "";
        }
        return contextPath;
    }

    /***
     * 通过名称，获取Spring IOC中的Bean对象
     *
     * @author 剑鸣秋朔
     * @date 2024/5/27 21:32
     * @param beanName beanName
     * @return T
     */
    public static <T> T getBean(String beanName) {
        return (T) AppContextAware.getInstance().getBean(beanName);
    }

    /***
     *  通过类型，获取Spring IOC中的Bean对象
     *
     * @author 剑鸣秋朔
     * @date 2024/5/27 21:32
     * @param clazz clazz
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        return AppContextAware.getInstance().getBean(clazz);
    }

    /***
     * 通过类型和名称，获取Spring IOC中的Bean对象
     *
     * @author 剑鸣秋朔
     * @date 2024/5/27 21:32
     * @param clazz clazz
     * @param beanName beanName
     * @return T
     */
    public static <T> T getBean(Class<T> clazz, String beanName) {
        return AppContextAware.getInstance().getBean(beanName, clazz);
    }


}
