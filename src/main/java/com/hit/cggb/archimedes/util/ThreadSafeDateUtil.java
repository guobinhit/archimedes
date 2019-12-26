package com.hit.cggb.archimedes.util;

import com.hit.cggb.archimedes.enumtype.CalendarTypeEnum;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 4/3/19,7:34 PM
 * @description 线程安全的时间工具类
 */
public class ThreadSafeDateUtil {

    private static final Logger logger = LoggerFactory.getLogger(ThreadSafeDateUtil.class);

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_MILLISECOND_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String HOUR_MINUTE_FORMAT = "HH:mm";
    public static final String ES_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final DateTimeFormatter ES_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    // 东八区
    private static final String CHINA_TIME_ZONE = "GMT+8:00";

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();
    private static ThreadLocal<DateFormat> dateTime_threadLocal = new ThreadLocal<DateFormat>();

    /**
     * 获取 yyyy-MM-dd 格式
     *
     * @return DateFormat
     */
    public static DateFormat getDateFormat() {
        DateFormat df = threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(DATE_FORMAT);
            threadLocal.set(df);
        }
        return df;
    }

    /**
     * 获取 yyyy-MM-dd HH:mm:ss 格式
     *
     * @return DateFormat
     */
    public static DateFormat getDateTimeFormat() {
        DateFormat df = dateTime_threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(DATETIME_FORMAT);
            dateTime_threadLocal.set(df);
        }
        return df;
    }

    /**
     * 获取 yyyy-MM-dd HH:mm:ss.SSS 格式
     *
     * @return DateFormat
     */
    public static DateFormat getDateTimeMillisecondFormat() {
        DateFormat df = dateTime_threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(DATETIME_MILLISECOND_FORMAT);
            dateTime_threadLocal.set(df);
        }
        return df;
    }

    /**
     * 获取 HH:mm 格式
     *
     * @return DateFormat
     */
    public static DateFormat getHourMinuteFormat() {
        DateFormat df = threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(HOUR_MINUTE_FORMAT);
            threadLocal.set(df);
        }
        return df;
    }

    /**
     * 日期转字符串，精确到天
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return getDateFormat().format(date);
    }

    /**
     * 时间转字符串，精确到秒
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return getDateTimeFormat().format(date);
    }

    /**
     * 时间转字符串，精确到毫秒
     *
     * @param date
     * @return
     */
    public static String formatDateTimeMillisecond(Date date) {
        return getDateTimeMillisecondFormat().format(date);
    }

    /**
     * 时间转字符串，格式为时分
     *
     * @param date
     * @return
     */
    public static String formatHourMinute(Date date) {
        return getHourMinuteFormat().format(date);
    }

    /**
     * 字符串(精确到天)转成Date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }

    /**
     * 字符串(精确到秒)转成Date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(String strDate) throws ParseException {
        return getDateTimeFormat().parse(strDate);
    }

    /**
     * 字符串(精确到毫秒)转成Date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parseDateTimeMillisecond(String strDate) {
        try {
            return getDateTimeMillisecondFormat().parse(strDate);
        } catch (Throwable e) {
            logger.error("parse date to yyyy-MM-dd HH:mm:ss.SSS failed, return origin value " + strDate + ", error message is " + e);
            return new Date();
        }
    }

    /**
     * Date(精确到毫秒)转成Date
     *
     * @param data
     * @return
     * @throws ParseException
     */
    public static Date parseDateTimeMillisecond(Date data) {
        try {
            return getDateTimeMillisecondFormat().parse(formatDateTimeMillisecond(data));
        } catch (Throwable e) {
            logger.error("parse date to yyyy-MM-dd HH:mm:ss.SSS failed, return origin value " + data + ", error message is " + e);
            return data;
        }
    }

    /**
     * Date(时分)转成Date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parseHourMinute(String strDate) throws ParseException {
        return getHourMinuteFormat().parse(strDate);
    }

    /**
     * 字符串(时分)转成Date
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseHourMinute(Date date) throws ParseException {
        return getHourMinuteFormat().parse(formatHourMinute(date));
    }

    /**
     * 得到一个当前时间的延迟时间
     *
     * @param dateDelayMinutes 单位=分钟
     * @return
     */
    public static Date getFetchDelayDate(Integer dateDelayMinutes) {
        Calendar c = Calendar.getInstance();
        if (dateDelayMinutes > 0) {
            dateDelayMinutes = -dateDelayMinutes;
        }
        c.add(Calendar.MINUTE, dateDelayMinutes);
        return c.getTime();
    }

    /**
     * date前移dateDelayMinutes
     *
     * @param date
     * @param dateDelayMinutes 单位=分钟
     * @return
     */
    public static Date getFetchDelayDate(Date date, Integer dateDelayMinutes) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (dateDelayMinutes > 0) {
            dateDelayMinutes = -dateDelayMinutes;
        }
        c.add(Calendar.MINUTE, dateDelayMinutes);
        return c.getTime();
    }

    /**
     * 获取当前（东八区）时间
     *
     * @return 北京时间
     */
    public static Date getCurrentDate() {
        return getCurrentDate(Locale.CHINA);
    }

    /**
     * 获取指定时区当前时间
     *
     * @param aLocale 地区标识，例如 Locale.CHINA
     * @return 指定时区当前时间
     */
    public static Date getCurrentDate(Locale aLocale) {
        Calendar calendar = Calendar.getInstance(aLocale);
        return calendar.getTime();
    }

    /**
     * 获取指定时间的偏移时间
     *
     * @param date         指定时间
     * @param calendarType 偏移类型
     * @param dateFormat   时间格式
     * @param n            偏移天数，n 为正数则后移，n 为负数则前移
     * @return 偏移时间
     */
    public static Date getDeviationTime(Date date, CalendarTypeEnum calendarType, String dateFormat, int n) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone(CHINA_TIME_ZONE));
            calendar.setTime(date);

            // 设置偏移时间类型
            if (CalendarTypeEnum.YEAR.equals(calendarType)) {
                calendar.add(Calendar.YEAR, n);
            } else if (CalendarTypeEnum.MONTH.equals(calendarType)) {
                calendar.add(Calendar.MONTH, n);
            } else if (CalendarTypeEnum.DATE.equals(calendarType)) {
                calendar.add(Calendar.DATE, n);
            } else if (CalendarTypeEnum.HOUR.equals(calendarType)) {
                calendar.add(Calendar.HOUR, n);
            } else if (CalendarTypeEnum.MINUTE.equals(calendarType)) {
                calendar.add(Calendar.MINUTE, n);
            } else if (CalendarTypeEnum.SECOND.equals(calendarType)) {
                calendar.add(Calendar.SECOND, n);
            } else if (CalendarTypeEnum.MILLISECOND.equals(calendarType)) {
                calendar.add(Calendar.MILLISECOND, n);
            } else {
                logger.error("meet unknown CalendarTypeEnum of " + calendarType);
                return null;
            }

            // 获取偏移时间
            date = calendar.getTime();

            // 获取指定时间格式
            DateFormat pointedDateFormat = getPointedDateFormat(dateFormat);
            String migrationDateStr = pointedDateFormat.format(date);

            return pointedDateFormat.parse(migrationDateStr);
        } catch (Throwable e) {
            logger.error("get date " + date + ", " + n + " " + calendarType + " deviation time come across a error, message is " + e);
            return null;
        }
    }

    /**
     * 获取指定时间格式
     *
     * @param dateFormat 时间格式字符串
     * @return DateFormat
     */
    public static DateFormat getPointedDateFormat(String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        // 设置东八区时区
        df.setTimeZone(TimeZone.getTimeZone(CHINA_TIME_ZONE));
        return df;
    }

    /**
     * 将 Elasticsearch 时间转为 Java 时间
     *
     * @param time ES 格式时间
     * @return Java 格式时间
     */
    public static long convertEsTime2JavaTime(String time) {
        DateTime timestamp = ES_TIME_FORMATTER.parseDateTime(time);
        DateTime dateTime = timestamp.plusHours(8);
        return dateTime.getMillis();
    }

    /**
     * 将 Java 时间转为 Elasticsearch 时间
     *
     * @param time Java 格式时间
     * @return ES 格式时间
     */
    public static String convertJavaTime2EsTime(String time) {
        try {
            Date date = getDeviationTime(
                    parseDateTime(time),
                    CalendarTypeEnum.HOUR,
                    DATETIME_FORMAT,
                    -8);

            String dateStr = formatDateTime(date);
            String[] dateStrArray = dateStr.split(" ");
            return dateStrArray[0] + "T" + dateStrArray[1];
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Date date = new Date();
        System.out.println(date);
        Date date1 = parseDateTimeMillisecond(date);
        System.out.println(date1);
    }
}
