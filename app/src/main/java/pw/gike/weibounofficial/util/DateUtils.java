package pw.gike.weibounofficial.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {

//    String dateFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 一些时间格式
     */
    public final static String FORMAT_TIME = "HH:mm";
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";
    public final static String FORMAT_DATE_TIME_SECOND = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_MONTH_DAY_TIME = "MM-dd HH:mm";
    public final static String FORMAT_DATE = "yyyy-MM-dd";

    /**
     * 获取系统时间戳
     *
     * @return 系统时间戳
     */
    public long getCurTimeLong() {
        long time = System.currentTimeMillis();
        return time;
    }

    /**
     * 获取当前时间
     *
     * @param dateFormatStr 时间格式
     * @return 当前时间
     */
    public static String getCurDate(String dateFormatStr) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(dateFormatStr);
        return sDateFormat.format(new java.util.Date());
    }

    /**
     * 时间戳转换成字符串
     *
     * @param milSecond     时间戳
     * @param dateFormatStr 时间格式
     * @return 时间字符串
     */
    public static String getDateToString(long milSecond, String dateFormatStr) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(dateFormatStr);
        return format.format(date);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param dateString    字符串
     * @param dateFormatStr 时间格式
     * @return 时间戳
     */
    public static long getStringToDate(String dateString, String dateFormatStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }
}