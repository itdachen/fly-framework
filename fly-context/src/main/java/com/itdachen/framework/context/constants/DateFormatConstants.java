package com.itdachen.framework.context.constants;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Description: 日期格式化
 * Created by 王大宸 on 2023/02/12 22:58
 * Created with IntelliJ IDEA.
 */
public class DateFormatConstants {

    public static final String DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATTER_PATTERN = "yyyy-MM-dd";
    public static final String TIME_FORMATTER_PATTERN = "HH:mm:ss";
    public static final SimpleDateFormat S_DATE_TIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat S_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat S_TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");


}
