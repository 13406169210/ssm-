package com.moonhu.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期类型转换器
 * 定义完成之后需要在spring-mvc中配置
 */
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String dateString) {

        Date parseDate = null;
       if (!"".equals(dateString)){
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
           try {
               parseDate = simpleDateFormat.parse(dateString);
           } catch (ParseException e) {
               e.printStackTrace();
           }
       }
        return parseDate;
    }
}
