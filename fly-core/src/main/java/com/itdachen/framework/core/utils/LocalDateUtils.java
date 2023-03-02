package com.itdachen.framework.core.utils;

import com.itdachen.framework.context.constants.DateFormatConstants;
import org.apache.commons.lang3.RandomUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Description:
 * Created by 王大宸 on 2023/02/12 23:00
 * Created with IntelliJ IDEA.
 */
public class LocalDateUtils {

    /***
     * 获取今天日期
     *
     * @author 王大宸
     * @date 2023/2/12 23:01
     * @return java.time.LocalDate
     */
    public static LocalDate today() {
        return LocalDate.now();
    }

    /***
     * 今天当前时间
     *
     * @author 王大宸
     * @date 2023/2/12 23:48
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime toDay() {
        return LocalDateTime.now();
    }

    /***
     * 明天当前时间
     *
     * @author 王大宸
     * @date 2023/2/12 23:46
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime tomorrow() {
        return toDay().plusDays(1);
    }

    /***
     * 昨天当前时间
     *
     * @author 王大宸
     * @date 2023/2/12 23:47
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime yesterday() {
        return toDay().plusDays(-1);
    }

    /***
     * 获取当前年
     *
     * @author 王大宸
     * @date 2023/2/12 23:02
     * @return int
     */
    public static int getYear() {
        return today().getYear();
    }

    /***
     * 获取当前月
     *
     * @author 王大宸
     * @date 2023/2/12 23:02
     * @return int
     */
    public static int getMonthValue() {
        return today().getMonthValue();
    }

    /***
     * 获取当前月当前日(获取今天是本月的几号)
     *
     * @author 王大宸
     * @date 2023/2/12 23:03
     * @return int
     */
    public static int getDayOfMonth() {
        return today().getDayOfMonth();
    }

    /***
     * 设置特定日期
     *
     * @author 王大宸
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
     * @author 王大宸
     * @date 2023/2/12 23:12
     * @param month  指定月
     * @param dayOfMonth 指定天
     * @return java.time.MonthDay
     */
    public static MonthDay setMonthDat(int month, int dayOfMonth) {
        return MonthDay.of(month, dayOfMonth);
    }

    /***
     * 获取一周以后
     *
     * @author 王大宸
     * @date 2023/2/12 23:14
     * @return java.time.LocalDate
     */
    public static LocalDate afterWeekDay() {
        return today().plus(1, ChronoUnit.WEEKS);
    }

    /***
     * 一年以后
     *
     * @author 王大宸
     * @date 2023/2/12 23:14
     * @return java.time.LocalDate
     */
    public static LocalDate afterYearDay() {
        return today().plus(1, ChronoUnit.YEARS);
    }

    /***
     * 一年之前
     *
     * @author 王大宸
     * @date 2023/2/12 23:15
     * @return java.time.LocalDate
     */
    public static LocalDate beforeYearDay() {
        return today().minus(1, ChronoUnit.YEARS);
    }

    /***
     * 是否闰年
     *
     * @author 王大宸
     * @date 2023/2/12 23:16
     * @return java.lang.Boolean
     */
    public static Boolean isLeapYear() {
        return today().isLeapYear();
    }

    /***
     * 将 LocalDateTime 转成字符串
     *
     * @author 王大宸
     * @date 2023/2/12 23:25
     * @param localDateTime localDateTime
     * @return java.lang.String
     */
    public static String localDateTime(LocalDateTime localDateTime) {
        return DateFormatConstants.S_DATE_TIME_FORMATTER.format(localDateTime);
    }

    /***
     * 获取今天日期
     *
     * @author 王大宸
     * @date 2023/2/12 23:24
     * @param localDateTime localDateTime
     * @return java.lang.String
     */
    public static String today(LocalDateTime localDateTime) {
        return DateFormatConstants.S_DATE_TIME_FORMATTER.format(today());
    }

    /***
     * 字符串时间转 LocalDateTime
     *
     * @author 王大宸
     * @date 2023/2/12 23:28
     * @param localDateTime localDateTime
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String localDateTime) {
        return LocalDateTime.parse(localDateTime, DateFormatConstants.DATE_TIME_FORMATTER);
    }

    /***
     * Date 转 LocalDateTime
     *
     * @author 王大宸
     * @date 2023/2/12 23:29
     * @param date date
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /***
     * LocalDateTime 转 Date
     *
     * @author 王大宸
     * @date 2023/2/12 23:31
     * @param localDateTime localDateTime
     * @return java.util.Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /***
     * LocalDate 转 Date
     *
     * @author 王大宸
     * @date 2023/2/12 23:32
     * @param localDate localDate
     * @return java.util.Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /***
     * 获取本月第一天
     *
     * @author 王大宸
     * @date 2023/2/12 23:43
     * @param localDate localDate
     * @return java.lang.String
     */
    public static String monthStart(LocalDate localDate) {
        return DateFormatConstants.S_DATE_TIME_FORMATTER.format(LocalDate.of(localDate.getYear(), localDate.getMonth(), 1));
    }

    /***
     * 获取本月最后一天
     *
     * @author 王大宸
     * @date 2023/2/12 23:43
     * @param localDate localDate
     * @return java.lang.String
     */
    public static String monthEnd(LocalDate localDate) {
        return DateFormatConstants.S_DATE_TIME_FORMATTER.format(localDate.with(TemporalAdjusters.lastDayOfMonth()));
    }

    /***
     * LocalDateTime 生成指定时间段的随机时间
     *
     * @author 王大宸
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

    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String localTime = df.format(time);
        LocalDateTime ldt = LocalDateTime.parse("2022-12-30 10:12:05", df);
        System.out.println("LocalDateTime转成String类型的时间：" + localTime);
        System.out.println("String类型的时间转成LocalDateTime：" + ldt);
    }


}
