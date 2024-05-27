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
     * 应用启动
     *
     * @author 王大宸
     * @date 2024/5/27 21:46
     * @param bootstrapContext bootstrapContext
     * @return void
     */
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("spring boot bootstrap starting");
        application.setBannerMode(Banner.Mode.OFF); // 禁用 Banner

        /* 启动及时, 用不到, 保留方法, 以后可能会用到该方法 */
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        stopWatch.stop();
//        double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
    }

    /***
     * 应用启动, 环境准备
     *
     * @author 王大宸
     * @date 2024/5/27 21:47
     * @param bootstrapContext bootstrapContext
     * @param environment environment
     * @return void
     */
    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        //   System.out.println("spring boot bootstrap environmentPrepared");

        final String runLog = SeparatorLine.SEPARATOR_LINE + "\n" +
                "\twelcome use [ " + environment.getProperty("spring.application.name") + " ] application\n" +
                "\tapplication [ " + environment.getProperty("spring.application.name") + " ] starting\n" +
                "\tstarting time " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n" +
                "\tframework from https://github.com/itdachen \n" +
                SeparatorLine.SEPARATOR_LINE;

        System.out.println(runLog);
    }

    /***
     * 应用上下文准备
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
     * 应用上下文加载
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

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        // System.out.println("spring boot bootstrap started");
    }

    /***
     * 应用启动成功
     *
     * @author 王大宸
     * @date 2024/5/27 21:46
     * @param context context
     * @param timeTaken timeTaken
     * @return void
     */
    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        /* 启动耗时, 单位: 秒 */
        long seconds = timeTaken.getSeconds();
      //  logger.info("spring boot application successfully started");
        ConfigurableEnvironment environment = context.getEnvironment();
        final String http = environment.getProperty("server.ssl.key-store") != null ? "https" : "http";
        final String port = environment.getProperty("server.port");
        final String contextPath = environment.getProperty("server.servlet.context-path") == null ? "" : environment.getProperty("server.servlet.context-path");

        logger.info("\n" + SeparatorLine.SEPARATOR_LINE + "\n\t" +
                        "application [ {} ] successfully started \n\t" +
                        "time consuming: \t{} s \n\t" +
                        "profiles: \t\t{}\n\t" +
                        "thread: \t\t\t{}\n\t" +
                        "request address: \t{}\n" + SeparatorLine.SEPARATOR_LINE,
                environment.getProperty("spring.application.name"),
                seconds,
                environment.getActiveProfiles().length == 0 ? "[ bootstrap | application | nacos ]" : environment.getActiveProfiles(),
                ManagementFactory.getRuntimeMXBean().getPid(),
                http + "://" + getHostIp() + ":" + port + contextPath
        );
    }

    /***
     * 应用启动失败
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
