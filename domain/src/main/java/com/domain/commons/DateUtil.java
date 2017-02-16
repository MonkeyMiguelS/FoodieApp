package com.domain.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

    public static String mapDateToString(Date date, String formatDate){
        return getFormatDate(date, formatDate);
    }

    public static String currentDateToString(String formatDate){
        return getFormatDate(Calendar.getInstance().getTime(), formatDate);
    }

    public static Date mapStringToDate(String dateStr, String formatDate) throws ParseException {
        return new SimpleDateFormat(formatDate).parse(dateStr);
    }

    private static String getFormatDate(Date date, String format){
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

}
