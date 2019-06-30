package com.moonhu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定义时间和字符串之间转换的类
 */
public class DateStringUtils {

    /**
     * 将date格式的数据转换成字符换类型
     *
     * @param date    date
     * @param pattern 转换格式
     * @return 转换之后的字符串
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }


    /**
     * 将字符串类型转化成date类型
     *
     * @param dateString 时间字符串
     * @param pattern    转换类型
     * @return 返回date类型数据
     * @throws ParseException 异常
     */
    public static Date stringToDate(String dateString, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(dateString);
    }

}
