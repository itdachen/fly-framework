package com.github.itdachen.boot.run.banner;

import com.github.itdachen.boot.run.banner.txt.VisitingCardTxt;
import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * Description: 项目默认启动 Banner
 * * 生成地址: https://www.bootschool.net/ascii
 * Created by 王大宸 on 2022-11-24 17:00
 * Created with IntelliJ IDEA.
 */
//@Configuration
public class SpringBootBootstrapBanner implements Banner {

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        out.println(VisitingCardTxt.banner(environment));
    }

}
