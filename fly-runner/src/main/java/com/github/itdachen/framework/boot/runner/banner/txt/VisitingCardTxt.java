package com.github.itdachen.framework.boot.runner.banner.txt;

import com.github.itdachen.framework.boot.runner.constants.SeparatorLine;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description: 默认 banner
 * Created by 王大宸 on 2023/03/19 14:11
 * Created with IntelliJ IDEA.
 */
public class VisitingCardTxt {


    public static String banner(Environment environment) {
        return SeparatorLine.SEPARATOR_LINE + "\n" +
                "\twelcome use [ " + environment.getProperty("spring.application.name") + " ] application\n" +
                "\tapplication [ " + environment.getProperty("spring.application.name") + " ] starting\n" +
                "\tstarting time " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n" +
                "\tframework from https://github.com/itdachen \n" +
                SeparatorLine.SEPARATOR_LINE;
    }

}
