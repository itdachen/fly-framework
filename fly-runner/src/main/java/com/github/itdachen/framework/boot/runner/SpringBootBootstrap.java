package com.github.itdachen.framework.boot.runner;

import com.github.itdachen.framework.boot.runner.banner.SpringBootBootstrapBanner;
import com.github.itdachen.framework.boot.runner.constants.SeparatorLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import org.springframework.util.StopWatch;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description: 项目启动成功后, 打印日志
 * Created by 王大宸 on 2023/02/26 23:55
 * Created with IntelliJ IDEA.
 */
public class SpringBootBootstrap {
    private static final Logger logger = LoggerFactory.getLogger(SpringBootBootstrap.class);

    public static void run(Class<?> clazz, String... args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Environment env = new SpringApplicationBuilder(clazz)
                .logStartupInfo(false)
                .banner(new SpringBootBootstrapBanner())
                .run(args)
                .getEnvironment();

        String http = env.getProperty("server.ssl.key-store") != null ? "https" : "http";
        String port = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path") == null ? "" : env.getProperty("server.servlet.context-path");
        stopWatch.stop();

        logger.info("\n" + SeparatorLine.SEPARATOR_LINE + "\n\t" +
                        "application [ {} ] is running \n\t" +
                        "time consuming: \t{} s \n\t" +
                        "profile(s): \t\t{}\n\t" +
                        "thread: \t\t\t{}\n\t" +
                        "request address: \t{}\n" + SeparatorLine.SEPARATOR_LINE,
                env.getProperty("spring.application.name"),
                stopWatch.getTotalTimeSeconds(),
                env.getActiveProfiles().length == 0 ? "[ bootstrap | application | nacos ]" : env.getActiveProfiles(),
                ManagementFactory.getRuntimeMXBean().getPid(),
                http + "://" + getHostIp() + ":" + port + contextPath
        );
    }

    private static String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }

}
