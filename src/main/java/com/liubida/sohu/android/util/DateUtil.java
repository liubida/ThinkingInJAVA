/*
 * Copyright 2012 sohu.com All right reserved. This software is the
 * confidential and proprietary information of sohu.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with sohu.com.
 */
package com.liubida.sohu.android.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liubida Aug 27, 2012 11:39:28 AM
 */
public class DateUtil {

    private static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static String DATETIME_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
    private static String DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    private static String DATETIMEFORMAT = "yyyyMMdd";
    private static String DATETIMEFORMAT_HH = "yyMMddHH";
    private static String DATE_FORMAT = "yyyy-MM-dd";
    private static String DATE_FORMAT_MONTH = "yyyy-MM";
    private static String DATETIME_FORMAT_T_SECOND = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * Get the previous time, from how many days to now.
     * 
     * @param days How many days.
     * @return The new previous time.
     */
    public static Date previous(int days) {
        return new Date(System.currentTimeMillis() - days * 3600000L * 24L);
    }

    /**
     * Convert date and time to string like "yyyy-MM-dd HH:mm:ss".
     */
    public static String formatDateTime(Date d) {
        return formatDate(d, DATETIME_FORMAT_SECOND);
    }

    /**
     * Convert date and time to string like "yyyy-MM-dd HH:mm".
     */
    public static String formatDateTimeNoSec(Date d) {
        return formatDate(d, DATETIME_FORMAT);
    }

    /**
     * Convert date and time to string like "yyyyMMddHH".
     * 
     * @param d
     * @return
     */
    public static String formatDateTimeHH(Date d) {
        return formatDate(d, DATETIMEFORMAT_HH);
    }

    /**
     * Convert date and time to string like "yyyyMMdd".
     * 
     * @param d
     * @return
     */
    public static String formatDateTimeDD(Date d) {
        return formatDate(d, DATETIMEFORMAT);
    }

    /**
     * Convert date and time to string like "yyyyMMddHHmmss".
     */
    public static String formatDateToString(Date d) {
        return formatDate(d, DATE_TIME_FORMAT);
    }

    /**
     * Convert date and time to string like "yyyy-MM-dd HH:mm".
     */
    public static String formatDateTime(long d) {
        return formatDate(d, DATETIME_FORMAT);
    }

    /**
     * Convert date to String like "yyyy-MM-dd".
     */
    public static String formatDate(Date d) {
        return formatDate(d, DATE_FORMAT);
    }

    /**
     * Convert date to String like "yyyy-MM".
     */
    public static String formatDateMonth(Date d) {
        return formatDate(d, DATE_FORMAT_MONTH);
    }

    public static String formatDate(Date d, String dataFormat) {
        return new SimpleDateFormat(dataFormat).format(d);
    }

    public static String formatDate(long d, String dataFormat) {
        return new SimpleDateFormat(dataFormat).format(d);
    }

    /**
     * Parse date like "yyyy-MM-dd".
     */
    public static Date parseDate(String d) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(d);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Parse date like "yyyy-MM-dd" to "yyyy-MM-dd 00:00".
     */
    public static Date parseDateBegin(Date d) {
        try {
            String str_date = formatDate(d) + " 00:00";
            return parseDateTime(str_date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Parse date like "yyyy-MM-dd" to "yyyy-MM-dd 23:59".
     */
    public static Date parseDateEnd(Date d) {
        try {
            String str_date = formatDate(d) + " 23:59";
            return parseDateTime(str_date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Convert date and time to string like "yyyy-MM-dd HH:mm:ss".
     */
    public static String formatDateToStringHMS(Date d) {
        return new SimpleDateFormat(DATETIME_FORMAT_SECOND).format(d);
    }

    /**
     * Parse date like "yyyy-MM-dd" .
     * 
     * @throws ParseException
     */
    public static Date paraseStringToDate(String d) throws ParseException {
        return parse(DATE_FORMAT, d);
    }

    /**
     * Parse date and time like "yyyy-MM-dd hh:mm".
     * 
     * @throws ParseException
     */
    public static Date parseDateTime(String d) throws ParseException {
        return parse(DATETIME_FORMAT, d);
    }

    /**
     * Parse date like "yyyy-MM-dd HH:mm:ss" .
     * 
     * @throws ParseException
     */
    public static Date parseStringToDateHMS(String d) throws ParseException {
        return parse(DATETIME_FORMAT_SECOND, d);
    }

    public static Date parseStringToDateTHMS(String d) throws ParseException {
        return parse(DATETIME_FORMAT_T_SECOND, d);
    }

    public static Date parse(String f, String d) throws ParseException {
        return new SimpleDateFormat(f).parse(d);
    }
}
