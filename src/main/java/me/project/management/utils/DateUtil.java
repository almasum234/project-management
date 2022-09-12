package me.project.management.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Util class for different type of operation
 *
 * @author masum
 * @version 1.0
 * @since 05-06-2020
 */
public class DateUtil {

    /**
     * Date formatter for date without time zone.
     * The format used is <tt>mm/dd/yyyy</tt>.
     */
    public static final DateFormat DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructor.
     */
    private DateUtil() {
    }

    /**
     * This method used convert string to date
     *
     * @param str String date value
     * @return date Date
     */
    public static Date strToDt(String str) {
        Date dt = null;
        try {
            if (str != null && str.length() > 7) {
                dt = DATA_FORMAT.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * This method used convert date to string
     *
     * @param dt date
     * @return string Date
     */
    public static String dtToStr(Date dt) {
        String str = null;
        if (dt != null) {
            str = DATA_FORMAT.format(dt);
        }
        return str;
    }

    /**
     * Change date by adding month
     *
     * @param date Date
     * @param addMonth Integer
     * @return date
     */
    public static Date addMonth(Date date, Integer addMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, addMonth);
        return calendar.getTime();
    }

    /**
     * get default future last date.
     *
     * @return Date
     */
    public static Date getDefaultFirstDate() {
        return DateUtil.strToDt("01/01/2000");
    }

    /**
     * get default future last date.
     *
     * @return Date
     */
    public static Date getDefaultFutureDate() {
        return DateUtil.strToDt("01/01/2900");
    }


}
