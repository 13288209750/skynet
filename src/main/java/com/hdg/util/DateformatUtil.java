package com.hdg.util;


import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * Created by BlueBuff on 2017/7/14.
 */
public class DateformatUtil {

    private static final String YYYY_MM_DD_HH_mm_ss="yyyy-MM-dd HH:mm:ss";

    private static final String YYYY_MM_DD="yyyy-MM-dd";

    /**
     * 将时间格式化成字符串
     * @param date
     * @param regex
     * @return
     */
    public static String formatToStringByCustom(Date date,String regex){
        return format(date,regex);
    }

    /**
     * 将时间格式化成字符串
     * @param date
     * @return
     */
    public static String formatToStringByShort(Date date){
        return format(date,YYYY_MM_DD);
    }

    /**
     * 将时间格式化成字符串
     * @param date
     * @return
     */
    public static String formatToStringByFull(Date date){
        return format(date,YYYY_MM_DD_HH_mm_ss);
    }

    /**
     * 根据自定义的格式解析成时间
     * @param timeStr
     * @param regex
     * @return
     * @throws ParseException
     */
    public static Date paseToDateCustom(String timeStr,String regex) throws ParseException {
        return parse(timeStr,regex);
    }

    /**
     * 解析成长类型的日期类型
     * @param timeStr
     * @return
     * @throws ParseException
     */
    public static Date parseToDateFull(String timeStr) throws ParseException {
        return parse(timeStr,YYYY_MM_DD_HH_mm_ss);
    }

    /**
     * 解析成短类型的格式的字符串
     * @param timeStr
     * @return
     * @throws ParseException
     */
    public static Date parseToDateShort(String timeStr) throws ParseException {
        return parse(timeStr,YYYY_MM_DD_HH_mm_ss);
    }

    /**
     * 时间解析
     * @param timeStr
     * @param regex
     * @return
     * @throws ParseException
     */
    private static Date parse(String timeStr,String regex) throws ParseException {
        if(StringUtils.isBlank(timeStr)||StringUtils.isBlank(regex)){
            return null;
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(regex);
        Date date=simpleDateFormat.parse(timeStr);
        return date;
    }

    /**
     * 将字符串转换成字符串
     * @param date
     * @param regex
     * @return
     */
    private static String format(Date date,String regex){
        if(date==null||StringUtils.isBlank(regex)){
            return "";
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(regex);
        return simpleDateFormat.format(date);
    }
}
