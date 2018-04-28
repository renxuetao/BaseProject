package com.lenovo.video.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 47250 on 2018/1/3.
 */
public class DateTimeUtil {

    public static String mToTime(int sss) {
        String hh;
        String mm;
        String ss;
        int h = sss / 3600;
        int m = (sss - h * 3600) / 60;
        int s = (sss - h * 3600) % 60;
        if (h < 10) {
            hh = "0" + h;
        } else {
            hh = "" + h;
        }
        if (m < 10) {
            mm = "0" + m;
        } else {
            mm = "" + m;
        }
        if (s < 10) {
            ss = "0" + s;
        } else {
            ss = "" + s;
        }

        return hh + ":" + mm + ":" + ss;
    }

    /**
     * 获取指定时间与现在时间的差值
     *
     * @param hour 指定时间的小时值
     * @param min  指定时间的分钟值
     */
    public static long method1(int hour, int min) {
        long l = 7200000;
        // 获取系统年月日
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd ");
        Date now = new Date();
        String time = myFmt.format(now);
        time = time + hour + ":" + min;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date;
            date = sdf.parse(time);
            // 获取指定时间的毫秒值
            long longDate = date.getTime();
            l += longDate - System.currentTimeMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }
}
