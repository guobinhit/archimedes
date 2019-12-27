package com.hit.cggb.archimedes.util;

import com.hit.cggb.archimedes.enumtype.CalendarTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
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

    // 时间格式化字符串
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_MILLISECOND_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String HOUR_MINUTE_FORMAT = "HH:mm";
    public static final String MONTH_DAY_FORMAT = "MM-dd";

    // 东八区
    private static final String CHINA_TIME_ZONE = "GMT+8:00";

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();
    private static ThreadLocal<DateFormat> dateTime_threadLocal = new ThreadLocal<DateFormat>();

    // 线程本地变量
    private static ThreadLocal<DateFormat> THREAD_LOCAL = new ThreadLocal<DateFormat>();

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
     * 获取 yyyy-MM-dd 格式
     *
     * @return DateFormat
     */
    public static DateFormat getDateFormat() {
        return getPointedDateFormat(DATE_FORMAT);
    }

    /**
     * 获取 yyyy-MM-dd HH:mm:ss 格式
     *
     * @return DateFormat
     */
    public static DateFormat getDateTimeFormat() {
        return getPointedDateFormat(DATETIME_FORMAT);
    }

    /**
     * 获取 yyyy-MM-dd HH:mm:ss.SSS 格式
     *
     * @return DateFormat
     */
    public static DateFormat getDateTimeMillisecondFormat() {
        return getPointedDateFormat(DATETIME_MILLISECOND_FORMAT);
    }

    /**
     * 获取 HH:mm 格式
     *
     * @return DateFormat
     */
    public static DateFormat getHourMinuteFormat() {
        return getPointedDateFormat(HOUR_MINUTE_FORMAT);
    }

    /**
     * 获取 MM-dd 格式
     *
     * @return DateFormat
     */
    public static DateFormat getMonthDayFormat() {
        return getPointedDateFormat(MONTH_DAY_FORMAT);
    }

    /**
     * 将 Date 类型参数转为 yyyy-MM-dd 格式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return getDateFormat().format(date);
    }

    /**
     * 将 Date 类型参数转为 yyyy-MM-dd HH:mm:ss 格式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return getDateTimeFormat().format(date);
    }

    /**
     * 将 Date 类型参数转为 yyyy-MM-dd HH:mm:ss.SSS 格式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateTimeMillisecond(Date date) {
        return getDateTimeMillisecondFormat().format(date);
    }

    /**
     * 将 Date 类型参数转为 HH:mm 格式的字符串
     *
     * @param date
     * @return
     */
    public static String formatHourMinute(Date date) {
        return getHourMinuteFormat().format(date);
    }

    /**
     * 将 Date 类型参数转为 MM-dd 格式的字符串
     *
     * @param date
     * @return
     */
    public static String formatMonthDay(Date date) {
        return getMonthDayFormat().format(date);
    }

    /**
     * 将字符串类型的参数转为 yyyy-MM-dd 格式的 Date
     *
     * @param strDate
     * @return
     */
    public static Date parseDate(String strDate) {
        try {
            return getDateFormat().parse(strDate);
        } catch (Throwable e) {
            logger.error("parse date string type to yyyy-MM-dd format failed, return null, error message is " + e);
            return null;
        }
    }

    /**
     * 将 Date 类型的参数转为 yyyy-MM-dd 格式的 Date
     *
     * @param date
     * @return
     */
    public static Date parseDate(Date date) {
        try {
            return getDateFormat().parse(formatDate(date));
        } catch (Throwable e) {
            logger.error("parse date type to yyyy-MM-dd format failed, return null, error message is " + e);
            return null;
        }
    }

    /**
     * 将字符串类型的参数转为 yyyy-MM-dd HH:mm:ss 格式的 Date
     *
     * @param strDate
     * @return
     */
    public static Date parseDateTime(String strDate) {
        try {
            return getDateTimeFormat().parse(strDate);
        } catch (Throwable e) {
            logger.error("parse date string type to yyyy-MM-dd HH:mm:ss format failed, return null, error message is " + e);
            return null;
        }
    }

    /**
     * 将 Date 类型的参数转为 yyyy-MM-dd HH:mm:ss 格式的 Date
     *
     * @param date
     * @return
     */
    public static Date parseDateTime(Date date) {
        try {
            return getDateTimeFormat().parse(formatDateTime(date));
        } catch (Throwable e) {
            logger.error("parse date type to yyyy-MM-dd HH:mm:ss format failed, return null, error message is " + e);
            return null;
        }
    }

    /**
     * 将字符串类型的参数转为 yyyy-MM-dd HH:mm:ss.SSS 格式的 Date
     *
     * @param strDate
     * @return
     */
    public static Date parseDateTimeMillisecond(String strDate) {
        try {
            return getDateTimeMillisecondFormat().parse(strDate);
        } catch (Throwable e) {
            logger.error("parse date string type to yyyy-MM-dd HH:mm:ss.SSS format failed, return null, error message is " + e);
            return null;
        }
    }

    /**
     * 将 Date 类型的参数转为 yyyy-MM-dd HH:mm:ss.SSS 格式的 Date
     *
     * @param date
     * @return
     */
    public static Date parseDateTimeMillisecond(Date date) {
        try {
            return getDateTimeMillisecondFormat().parse(formatDateTimeMillisecond(date));
        } catch (Throwable e) {
            logger.error("parse date type to yyyy-MM-dd HH:mm:ss.SSS format failed, return null, error message is " + e);
            return null;
        }
    }

    /**
     * 将字符串类型的参数转为 HH:mm 格式的 Date
     *
     * @param strDate
     * @return
     */
    public static Date parseHourMinute(String strDate) {
        try {
            return getHourMinuteFormat().parse(strDate);
        } catch (Throwable e) {
            logger.error("parse date string type to HH:mm format failed, return null, error message is " + e);
            return null;
        }
    }

    /**
     * 将 Date 类型的参数转为 HH:mm 格式的 Date
     *
     * @param date
     * @return
     */
    public static Date parseHourMinute(Date date) {
        try {
            return getHourMinuteFormat().parse(formatHourMinute(date));
        } catch (Throwable e) {
            logger.error("parse date type to HH:mm format failed, return null, error message is " + e);
            return null;
        }
    }

    /**
     * 将字符串类型的参数转为 MM-dd 格式的 Date
     *
     * @param strDate
     * @return
     */
    public static Date parseMonthDay(String strDate) {
        try {
            return getMonthDayFormat().parse(strDate);
        } catch (Throwable e) {
            logger.error("parse date string type to HH:mm format failed, return null, error message is " + e);
            return null;
        }
    }

    /**
     * 将 Date 类型的参数转为 MM-dd 格式的 Date
     *
     * @param date
     * @return
     */
    public static Date parseMonthDay(Date date) {
        try {
            return getMonthDayFormat().parse(formatMonthDay(date));
        } catch (Throwable e) {
            logger.error("parse date type to HH:mm format failed, return null, error message is " + e);
            return null;
        }
    }

    /**
     * 得到一个当前时间的延迟时间
     *
     * @param dateDelayMinutes 单位=分钟
     * @return
     */
    public static Date getFetchDelayDate(Integer dateDelayMinutes) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
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
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        if (dateDelayMinutes > 0) {
            dateDelayMinutes = -dateDelayMinutes;
        }
        c.add(Calendar.MINUTE, dateDelayMinutes);
        return c.getTime();
    }

    /**
     * 获取指定日期的零点时间
     *
     * @param n 日期偏移量，0 表示当天时间；正数表示后移时间；负数表示前移时间
     * @return 指定日期的零点时间，格式为 yyyy-MM-dd 00:00:00
     */
    public static Date getZeroOClockOfDate(int n) {
        try {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.setTime(date);
            calendar.add(Calendar.DATE, n);
            date = calendar.getTime();
            String startDateString = formatDate(date);
            startDateString = startDateString + " 00:00:00";
            return parseDateTime(startDateString);
        } catch (Throwable e) {
            logger.error("get { " + n + " days of today } zero O clock error, message is " + e);
            return null;
        }
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
     * 获取指定时区当前时间
     *
     * @param timeZone 时区字符串，例如 GMT+8:00
     * @return 指定时区当前时间
     */
    public static Date getCurrentDate(String timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        return calendar.getTime();
    }

    /**
     * 如果当前时分在参数时分之前，则返回 true
     * 如果当前时分在参数时分之后，则返回 false
     * <p>
     * 例如当前时分是 12:12，如果参数时分是 11:11，则返回 true；如果参数时分是 13:13，则返回 false
     *
     * @param targetDate    目标时间
     * @param hourMinuteStr 待比较时分为字符串，格式为 HH:mm
     * @return true or false
     */
    public static boolean compareHourMinute(Date targetDate, String hourMinuteStr) {
        try {
            // 获取目标时间的时分
            Date targetHourMinute = parseHourMinute(targetDate);
            // 解析待比较参数的时分
            Date paramHourMinute = parseHourMinute(hourMinuteStr);

            return targetHourMinute.before(paramHourMinute);
        } catch (Throwable e) {
            logger.error("compareWithCurrentHourMinute come across a error, return default value false, message is " + e);
            return false;
        }
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
                throw new IllegalArgumentException("meet unknown CalendarTypeEnum of " + calendarType);
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

    public static void main(String[] args) throws Exception {
        Date date = parseDateTime(new Date());
        System.out.println(formatDateTime(date));

        Date dive = getDeviationTime(date, CalendarTypeEnum.DATE, DATETIME_FORMAT, -3);
        System.out.println(formatDateTime(dive));

        System.out.println(formatMonthDay(new Date()));

        System.out.println(getZeroOClockOfDate(0));
        System.out.println(getZeroOClockOfDate(1));
        System.out.println(getZeroOClockOfDate(-1));
    }
}
