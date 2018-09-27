package com.jingyou.jybase.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class DateUtil {
    /**
     * 取当前日期yyyy-MM-dd
     * @return
     */
    public static String getCurrDate(){
        return getDate(new Date());
    }

    /**
     * 取当前时间HH:mm:ss
     * @return
     */
    public static String getCurrTime(){
        return getTime(new Date());
    }

    /**
     * 取当前日期时间yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrDateTime(){
        return getDateTime(new Date());
    }

    /**
     * 取当前期次
     * @return
     */
    public static String getCurrPeriod(){
        return getPeriod(new Date());
    }

    /**
     * 取上期期次
     * @return
     */
    public static String getPrevPeriod(String period){
        String year = period.substring(0,4);
        String month = period.substring(4,6);
        if(month.equals("01")){
            int intYear = Integer.parseInt(year);
            return (intYear-1)+"12";
        }else{
            int intMonth = Integer.parseInt(month);
            if(intMonth < 11){
                return year + "0" + (intMonth-1);
            }else{
                return year + (intMonth-1);
            }
        }
    }

    /**
     * 取日期yyyy-MM-dd
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    /**
     * 取日期yyyy-MM-dd
     * @param date
     * @return
     */
    public static String getPeriod(Date date) {
        return format(date, "yyyyMM");
    }


    /**
     * 取时间HH:mm:ss
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    /**
     * 取日期时间yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String getDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 取星期
     * @return
     */
    public static String getWeek(){
        return getWeek(new Date());
    }

    /**
     * 取星期
     * @return
     */
    public static String getWeek(Date date){
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        return dateFm.format(date);
    }

    /**
     * 取日期、星期 如 2015年06月20日 星期一
     * @return
     */
    public static String getDateWeek(){
        Date date = new Date();
        return format(date,"yyyy年MM月dd日 ") + getWeek(date);
    }

    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                DateFormat dateFormat = new SimpleDateFormat(format);
                result = dateFormat.format(date);
            }
        } catch (Exception localException) {
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getPrevPeriod("201609"));
    }
}
