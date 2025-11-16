package com.github.itdachen.framework.core.utils;

import com.github.itdachen.framework.context.constants.DateFormatConstants;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Description: 时间工具类
 * Created by 剑鸣秋朔 on 2023/02/12 23:00
 * Created with IntelliJ IDEA.
 */
public class LocalDateUtils {
    /**
     * 系统默认时区
     */
    public final static ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();
    /**
     * 默认日期格式
     */
    // private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateFormatConstants.DATE_TIME_FORMATTER_PATTERN);

    /***
     * 获取今天日期
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:01
     * @return java.time.LocalDate
     */
    public static LocalDate today() {
        return LocalDate.now();
    }

    /***
     * 今天当前时间
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:48
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime toDay() {
        return LocalDateTime.now();
    }

    /***
     * 获取今天日期
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:24
     * @return java.lang.String
     */
    public static String todayDateTime() {
        return DateFormatConstants.S_DATE_TIME_FORMATTER.format(toDay());
    }

    /***
     * 明天当前时间
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:46
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime tomorrow() {
        return toDay().plusDays(1);
    }

    /***
     * 昨天当前时间
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:47
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime yesterday() {
        return toDay().plusDays(-1);
    }

    /***
     * 获取当前年
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:02
     * @return int
     */
    public static int getYear() {
        return today().getYear();
    }

    /***
     * 获取当前月
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:02
     * @return int
     */
    public static int getMonthValue() {
        return today().getMonthValue();
    }

    /***
     * 获取当前月当前日(获取今天是本月的几号)
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:03
     * @return int
     */
    public static int getDayOfMonth() {
        return today().getDayOfMonth();
    }

    /***
     * 设置特定日期
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:05
     * @param year       特定日期, 年
     * @param month      特定日期: 月
     * @param dayOfMonth 特定日期: 日
     * @return java.time.LocalDate
     */
    public static LocalDate setSpecificDate(int year, int month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth);
    }

    /***
     * 指定某月某天, 例如: 2月12日
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:12
     * @param month  指定月
     * @param dayOfMonth 指定天
     * @return java.time.MonthDay
     */
    public static MonthDay setMonthDay(int month, int dayOfMonth) {
        return MonthDay.of(month, dayOfMonth);
    }

    /***
     * 获取一周以后
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:14
     * @return java.time.LocalDate
     */
    public static LocalDate afterWeekDay() {
        return today().plus(1, ChronoUnit.WEEKS);
    }

    /***
     * 一年以后
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:14
     * @return java.time.LocalDate
     */
    public static LocalDate afterYearDay() {
        return today().plus(1, ChronoUnit.YEARS);
    }

    /***
     * 一年之前
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:15
     * @return java.time.LocalDate
     */
    public static LocalDate beforeYearDay() {
        return today().minus(1, ChronoUnit.YEARS);
    }

    /***
     * 是否闰年
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:16
     * @return java.lang.Boolean
     */
    public static Boolean isLeapYear() {
        return today().isLeapYear();
    }


    /***
     * 获取当前时间的 string 类型
     *
     * @author 剑鸣秋朔
     * @date 2020/11/3 10:59
     * @param format 时间格式
     * @return java.lang.String
     */
    public static String getLocalDateTime(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    /***
     * 获取当前时间的 string 类型
     *
     * @author 剑鸣秋朔
     * @date 2020/11/3 10:58
     * @param
     * @return java.lang.String
     */
    public static String getLocalDateTime() {
        return getLocalDateTime(DateFormatConstants.DATE_TIME_FORMATTER_PATTERN);
    }

    public static String getLocalDateTimeMillis() {
        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();
        // 格式化日期时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatConstants.DATE_TIME_FORMATTER_PATTERN_MILLIS);
        return currentTime.format(formatter);
    }


    public static String getLocalDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DateFormatConstants.DATE_FORMATTER_PATTERN));
    }


    /***
     * 将 LocalDateTime 转成字符串
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:25
     * @param localDateTime localDateTime
     * @return java.lang.String
     */
    public static String toLocalDateTime(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatConstants.DATE_TIME_FORMATTER_PATTERN);
        return localDateTime.format(formatter);
    }

    /***
     * 字符串时间转 LocalDateTime
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:28
     * @param localDateTime localDateTime
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return LocalDateTime.parse(localDateTime, DateFormatConstants.DATE_TIME_FORMATTER);
    }

    /***
     * Date 转 LocalDateTime
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:29
     * @param date date
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /***
     * LocalDateTime 转 Date
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:31
     * @param localDateTime localDateTime
     * @return java.util.Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /***
     * LocalDate 转 Date
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:32
     * @param localDate localDate
     * @return java.util.Date
     */
    public static Date toDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /***
     * 将数字时间, 转成 Date 类型, 例如: 42156
     *
     * @author 剑鸣秋朔
     * @date 2023/6/9 10:30
     * @param numDays numDays
     * @return java.util.Date
     */
    public static Date toDate(Integer numDays) {
        if (null == numDays) {
            return null;
        }
        Calendar calendar = new GregorianCalendar(1900, Calendar.JANUARY, -1);
        return DateUtils.addDays(calendar.getTime(), numDays);
    }

    /***
     * 本月第一天
     *
     * @author 剑鸣秋朔
     * @date 2023/6/9 10:59

     * @return java.lang.String
     */
    public static String firstDayOfMonth() {
        return firstDayOfMonth(LocalDate.now());
    }

    /***
     * 根据年月, 获取第一天
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:43
     * @param localDate localDate
     * @return java.lang.String
     */
    public static String firstDayOfMonth(LocalDate localDate) {
        return DateFormatConstants.S_DATE_TIME_FORMATTER.format(LocalDate.of(localDate.getYear(), localDate.getMonth(), 1));
    }

    /***
     * 获取本月最后一天
     *
     * @author 剑鸣秋朔
     * @date 2023/6/9 11:01
     * @return java.lang.String
     */
    public static String lastDayOfMonth() {
        return lastDayOfMonth(LocalDate.now());
    }

    /***
     * 根据年月, 获取最后一天
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:43
     * @param localDate localDate
     * @return java.lang.String
     */
    public static String lastDayOfMonth(LocalDate localDate) {
        return DateFormatConstants.S_DATE_TIME_FORMATTER.format(localDate.with(TemporalAdjusters.lastDayOfMonth()));
    }

    /***
     * LocalDateTime 生成指定时间段的随机时间
     *
     * @author 剑鸣秋朔
     * @date 2023/2/12 23:44
     * @param startDay startDay
     * @param endDay endDay
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime randomLocalDateTime(int startDay, int endDay) {
        int plusMinus = 1;
        if (startDay < 0 && endDay > 0) {
            plusMinus = Math.random() > 0.5 ? 1 : -1;
            if (plusMinus > 0) {
                startDay = 0;
            } else {
                endDay = Math.abs(startDay);
                startDay = 0;
            }
        } else if (startDay < 0 && endDay < 0) {
            plusMinus = -1;
            //两个数交换
            startDay = startDay + endDay;
            endDay = startDay - endDay;
            startDay = startDay - endDay;
            //取绝对值
            startDay = Math.abs(startDay);
            endDay = Math.abs(endDay);
        }
        //指定时间
        LocalDate day = LocalDate.now().plusDays(plusMinus * RandomUtils.nextInt(startDay, endDay));
        int hour = RandomUtils.nextInt(1, 24);
        int minute = RandomUtils.nextInt(0, 60);
        int second = RandomUtils.nextInt(0, 60);
        LocalTime time = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(day, time);
    }


    /***
     * 将 Date 转成 LocalDate 类型
     *
     * @author 剑鸣秋朔
     * @date 2023/6/9 10:37
     * @param date date
     * @return java.time.LocalDate
     */
    public static LocalDate toLocalDate(Date date) {
        if (null == date) {
            return null;
        }
        // 将Date转为Instant对象
        final Instant instant = date.toInstant();
        // 获取LocalDate对象
        return instant.atZone(DEFAULT_ZONE_ID).toLocalDate();
    }

    /***
     * 将数字时间, 转成 LocalDate 类型, 例如: 42156
     *
     * @author 剑鸣秋朔
     * @date 2023/6/9 10:30
     * @param numDays numDays
     * @return java.time.LocalDate
     */
    public static LocalDate toLocalDate(Integer numDays) {
        if (null == numDays) {
            return null;
        }
        Date date = toDate(numDays);
        return toLocalDate(date);
    }

    /***
     * 将字符串类型转成 LocalDate 类型
     *
     * @author 剑鸣秋朔
     * @date 2023/6/9 10:51
     * @param value value
     * @return java.time.LocalDate
     */
    public static LocalDate toLocalDate(String value) {
        if (null == value) {
            return null;
        }
        return toLocalDate(value, "yyyy-MM-dd");
    }

    /***
     * 将字符串类型转成 LocalDate 类型
     *
     * @author 剑鸣秋朔
     * @date 2023/6/9 10:51
     * @param value  字符串数据
     * @param format 时间格式类型
     * @return java.time.LocalDate
     */
    public static LocalDate toLocalDate(String value, String format) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        value = value.replace("/", "-");
        String[] split = value.split("-");
        if (3 != split.length) {
            return null;
        }
        value = String.format("%04d", Integer.parseInt(split[0])) + "-"
                + String.format("%02d", Integer.parseInt(split[1])) + "-"
                + String.format("%02d", Integer.parseInt(split[2]));

        DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern(format).toFormatter();
        return LocalDate.parse(value, df);

    }

    /***
     * timestamp 转 字符串，默认日期格式
     *
     * @author 剑鸣秋朔
     * @date 2023/6/26 20:42
     * @param timestamp timestamp
     * @return java.lang.String
     */
    public static String format(long timestamp) {
        return DateFormatConstants.DATE_TIME_FORMATTER.format(new Date(timestamp).toInstant().atZone(DEFAULT_ZONE_ID));
    }

    /***
     * 获取当天剩余的秒数
     *
     * @author 剑鸣秋朔
     * @date 2023/6/26 20:43
     * @param currentDate 年月日时间
     * @return java.lang.Integer
     */
    public static Integer getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault())
                .plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }

    /***
     * 获取当天剩余的秒数
     *
     * @author 剑鸣秋朔
     * @date 2023/6/26 20:46
     * @param currentDate 年月日时间
     * @return java.lang.Integer
     */
    public static Integer getRemainSecondsOneDay(LocalDate currentDate) {
        return getRemainSecondsOneDay(toDate(currentDate));
    }

    /***
     * 今天剩余多少秒
     *
     * @author 剑鸣秋朔
     * @date 2023/6/26 20:46
     * @return java.lang.Integer
     */
    public static Integer getRemainSecondsInToDay() {
        return getRemainSecondsOneDay(LocalDate.now());
    }


    public static void main(String[] args) {
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime time = LocalDateTime.now();
//        String localTime = df.format(time);
//        LocalDateTime ldt = LocalDateTime.parse("2022-12-30 10:12:05", df);
//        System.out.println("LocalDateTime转成String类型的时间：" + localTime);
//        System.out.println("String类型的时间转成LocalDateTime：" + ldt);

        System.err.println(getRemainSecondsInToDay());

    }


}
