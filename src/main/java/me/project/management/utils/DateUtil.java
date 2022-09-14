package me.project.management.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Util class for different type of operation
 *
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
public class DateUtil {

    public static final DateFormat DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
            if (str != null) {
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

}
