package com.github.itdachen.framework.boot.runner.banner;

import com.github.itdachen.framework.boot.runner.constants.SeparatorLine;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description: 项目默认启动 Banner
 * * 生成地址: https://www.bootschool.net/ascii
 * Created by 王大宸 on 2022-11-24 17:00
 * Created with IntelliJ IDEA.
 */
@Configuration
public class SpringBootBootstrapBanner implements Banner {

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        String banner = SeparatorLine.SEPARATOR_LINE + "\n" +
                "\twelcome use [ " + environment.getProperty("spring.application.name") + " ] os\n" +
                "\tapplication [ " + environment.getProperty("spring.application.name") + " ] is starting\n" +
                "\tstarting time " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n" +
                "\ttechnical support by https://gitee.com/itdachen \n" +
                SeparatorLine.SEPARATOR_LINE;
        out.println(banner);
    }

}
