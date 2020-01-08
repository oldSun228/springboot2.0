package com.xch.study.utils;


import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author fgs
 * @data 2019/5/21 14:55
 */
public class AutoDateFormateUtils {

    public static String getCurTime(String dateFormate) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
        return sdf.format(new Date());
    }

    public static String getAutoTime(String dateFormate, long autoTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
        return sdf.format(new Date(autoTime));
    }

    public static String getAutoTime(String dateFormate, Date autoTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
        return sdf.format(autoTime);
    }

    public static long getAutoTime(String dateFormate, String autoTime) {
        long result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
        try {
            result = sdf.parse(sdf.format(autoTime)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static long getAutoTimeNoFormat(String dateFormate, String autoTime) {
        long result = 0;
        if (StringUtils.isNotEmpty(autoTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
            try {
                result = sdf.parse(autoTime).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String getAfterAutoTime(String dateFormate, Integer number) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, +number);
        return sdf.format(calendar.getTime());
    }


    public static String getCurTimeAfterDay(String dateFormate) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(calendar.getTime());
    }

    public static String[] getMonthDiff(String endTime, String startTime, String index) {
        String[] resultData = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy_MM");
        try {
            Date st = sdf.parse(startTime);
            Date et = sdf.parse(endTime);
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(et);
            c2.setTime(st);
            if (c1.getTimeInMillis() < c2.getTimeInMillis()) {
                return null;
            }
            int surplus = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
            int result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
            int month = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
            surplus = surplus <= 0 ? 1 : 0;


            int count = (Math.abs(month + result) + surplus);
            if (count > 0) {
                resultData = new String[count];
                for (int i = 1; i <= count; i++) {
                    Calendar c3 = Calendar.getInstance();
                    c3.setTime(st);
                    c3.add(Calendar.MONTH, i - 1);
                    resultData[i - 1] = index + sdf1.format(c3.getTime());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultData;
    }
}
