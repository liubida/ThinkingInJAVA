/*
 * Copyright 2012 sohu.com All right reserved. This software is the
 * confidential and proprietary information of sohu.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with sohu.com.
 */
package com.liubida.sohu.android.util;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liubida Aug 24, 2012 8:49:02 AM
 */
public class SohukanUtil {

    private static SohukanUtil instance;
    private static final String TAG = "SohukanUtil";

    public static <T> String listToString(List<T> list) {
        if (null == list || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T i : list) {
            sb.append(i.toString());
            sb.append(",");
        }
        int index = sb.lastIndexOf(",");
        if (-1 != index) {
            sb.deleteCharAt(index);
        }
        return sb.toString();
    }

    public static void replace(StringBuilder sb, String source, String target) {
        int index = sb.indexOf(source);
        int endIndex = index + source.length();
        sb.replace(index, endIndex, target);
    }

    public static void replaceAll(StringBuilder sb, String source, String target) {
        int index = 0;
        int endIndex = 0;
        int length = source.length();
        while (-1 != (index = sb.indexOf(source, index))) {
            endIndex = index + length;
            sb.replace(index, endIndex, target);
            index += target.length() + 5;
        }
    }

    public static long currentTime() {
        return System.currentTimeMillis();
    }

    public static String getHost(String url) {
        String host = "";
        try {
            URL temp_url = new URL(url);
            host = temp_url.getHost();
        } catch (Exception e) {
            System.out.println(url + ":get host failure");
        }
        return host;
    }

    public static String getTimeDisplay(String create_time) {

        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        String datetime = "";

        Calendar now = Calendar.getInstance();
        long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));// 毫秒数

        long createTime = Long.parseLong(create_time) * 1000;

        long diff = currentTime() - createTime;

        if (diff < ms)
            return "今天";
        else if (diff < (ms + 24 * 3600 * 1000))
            return "昨天";

        Date date = new Date(createTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        datetime = sdf.format(date);

        return datetime;
    }

    public static boolean isEmail(String userid) {
        Pattern p = Pattern
                           .compile(
                                    "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",
                                    Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(userid);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getDate(long time, String format) {
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        String datetime = "";
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        datetime = sdf.format(date);
        return datetime;
    }
}
