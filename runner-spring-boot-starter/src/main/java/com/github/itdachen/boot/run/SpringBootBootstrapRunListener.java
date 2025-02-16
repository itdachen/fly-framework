package com.github.itdachen.boot.run;

import com.github.itdachen.boot.run.constants.SeparatorLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 自定义启动日志模块
 *
 * @author 王大宸
 * @date 2024-01-08 21:19
 */
public class SpringBootBootstrapRunListener implements SpringApplicationRunListener {
    private static final Logger logger = LoggerFactory.getLogger(SpringBootBootstrapRunListener.class);

    private final SpringApplication application;
    private final String[] args;


    public SpringBootBootstrapRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    /***
     * run 方法第一次被执行时调用，早期初始化工作
     *
     * @author 王大宸
     * @date 2025/1/22 20:29
     * @param bootstrapContext bootstrapContext
     * @return void
     */
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        //   System.out.println("spring boot bootstrap starting");
        application.setBannerMode(Banner.Mode.OFF); // 禁用 Banner

        /* 启动及时, 用不到, 保留方法, 以后可能会用到该方法 */
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        stopWatch.stop();
//        double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
    }

    /***
     * environment 创建后，ApplicationContext 创建前
     *
     * @author 王大宸
     * @date 2024/5/27 21:47
     * @param bootstrapContext bootstrapContext
     * @param environment environment
     * @return void
     */
    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        String appName = environment.getProperty("spring.application.name");
        if (null == appName || "".equals(appName) || "null".equals(appName)) {
            return;
        }
        final String runLog = SeparatorLine.SEPARATOR_LINE + "\n" +
                "\twelcome use [ " + appName + " ] application\n" +
                "\tapplication [ " + appName + " ] starting\n" +
                "\tstarting time " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n" +
                "\tframework from https://github.com/itdachen \n" +
                SeparatorLine.SEPARATOR_LINE;

        System.out.println(runLog);
    }

    /***
     * ApplicationContext 实例创建，部分属性设置了
     *
     * @author 王大宸
     * @date 2024/5/27 21:47
     * @param context context
     * @return void
     */
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        // System.out.println("spring boot bootstrap contextPrepared");
    }

    /***
     * ApplicationContext 加载后，refresh 前
     *
     * @author 王大宸
     * @date 2024/5/27 21:47
     * @param context context
     * @return void
     */
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        //  System.out.println("spring boot bootstrap contextLoaded");
    }

    /***
     * refresh 后
     *
     * @author 王大宸
     * @date 2025/1/22 20:31
     * @param context context
     * @param timeTaken timeTaken
     * @return void
     */
    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        // System.out.println("spring boot bootstrap started");
    }

    /***
     * 所有初始化完成后，run 结束前
     *
     * @author 王大宸
     * @date 2024/5/27 21:46
     * @param context context
     * @param timeTaken timeTaken
     * @return void
     */
    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        ConfigurableEnvironment environment = context.getEnvironment();
        final String contextPath = environment.getProperty("server.servlet.context-path") == null ? "" : environment.getProperty("server.servlet.context-path");
        if (null == contextPath || "".equals(contextPath) || "null".equals(contextPath)) {
            return;
        }

        /* 启动耗时, 单位: 秒 */
        long seconds = timeTaken.getSeconds();
        //  logger.info("spring boot application successfully started");

        final String http = environment.getProperty("server.ssl.key-store") != null ? "https" : "http";
        final String port = environment.getProperty("server.port");
        final String activeProfiles = environment.getActiveProfiles().length == 0 ? "[ bootstrap | application | nacos ]" : Arrays.toString(environment.getActiveProfiles());
        final String serverHttp = http + "://" + getHostIp() + ":" + port + contextPath;
        String text = SeparatorLine.SEPARATOR_LINE + "\n\t" +
                "application [ " + environment.getProperty("spring.application.name") + " ] successfully started \n\t" +
                "time consuming: \t" + seconds + " s \n\t" +
                "profiles: \t\t\t" + activeProfiles + "\n\t" +
                "thread: \t\t\t" + ManagementFactory.getRuntimeMXBean().getPid() + "\n\t" +
                "request address: \t" + serverHttp + "\n" + SeparatorLine.SEPARATOR_LINE;

        System.out.println(text);
    }

    /***
     * 初始化失败后
     *
     * @author 王大宸
     * @date 2024/5/27 21:46
     * @param context context
     * @param exception exception
     * @return void
     */
    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        // logger.info("spring boot bootstrap failed");
    }

    /***
     * 获取服务器IP
     *
     * @author 王大宸
     * @date 2024/5/27 21:46
     * @return java.lang.String
     */
    private static String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }

}
