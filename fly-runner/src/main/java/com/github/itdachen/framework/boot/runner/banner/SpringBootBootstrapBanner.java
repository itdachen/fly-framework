package com.github.itdachen.framework.boot.runner.banner;

import com.github.itdachen.framework.boot.runner.banner.txt.VisitingCardTxt;
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
        out.println(VisitingCardTxt.banner(environment));
    }

}
