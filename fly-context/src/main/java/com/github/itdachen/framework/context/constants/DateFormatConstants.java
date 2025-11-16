package com.github.itdachen.framework.context.constants;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Description: 日期格式化
 * Created by 剑鸣秋朔 on 2023/02/12 22:58
 * Created with IntelliJ IDEA.
 */
public class DateFormatConstants {

    public static final String DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMATTER_PATTERN_MILLIS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMATTER_PATTERN = "yyyy-MM-dd";
    public static final String TIME_FORMATTER_PATTERN = "HH:mm:ss";


    public static final SimpleDateFormat S_DATE_TIME_FORMATTER = new SimpleDateFormat(DATE_TIME_FORMATTER_PATTERN);
    public static final SimpleDateFormat S_DATE_FORMATTER = new SimpleDateFormat(DATE_FORMATTER_PATTERN);
    public static final SimpleDateFormat S_TIME_FORMATTER = new SimpleDateFormat(TIME_FORMATTER_PATTERN);


    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMATTER_PATTERN);


}
