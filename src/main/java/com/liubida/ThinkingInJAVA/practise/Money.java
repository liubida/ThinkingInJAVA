/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.practise;

/**
 * @author liubida 2012-2-13 下午7:41:34
 */
public class Money {

    private static final String[] NUMBERS = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
    private static final String[] IUNIT   = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾",
            "佰", "仟"                     };
    private static final String[] DUNIT   = { "角", "分", "厘" };

    public static String toChinese(String str) {
        str = str.replaceAll(",", "");
        String integerStr = "";
        String decimalStr = "";
        int index = str.indexOf(".");
        if (index > 0) {
            integerStr = str.substring(0, index);
            decimalStr = str.substring(index + 1);
        } else if (index == 0) {
            integerStr = "";
            decimalStr = str.substring(index + 1);
        } else if (index < 0) {
            integerStr = str;
            decimalStr = "";
        }

        if (!integerStr.equals("")) {
            integerStr = Long.toString(Long.parseLong(integerStr));
            if (integerStr.equals("0")) {
                integerStr = "";
            }
        }

        if (integerStr.length() > IUNIT.length) {
            System.out.println("超出处理能力");
            return str;
        }

        int[] integers = toArray(integerStr);
        int[] decimals = toArray(decimalStr);
        boolean isMust5 = isMust5(integerStr);

        return getChineseInteger(integers, isMust5) + getChineseDecimal(decimals);
    }

    /**
     * 得到中文金额的小数部分。
     */
    private static String getChineseDecimal(int[] decimals) {
        StringBuffer chineseDecimal = new StringBuffer("");
        for (int i = 0; i < decimals.length; i++) {
            // 舍去3位小数之后的
            if (i == 3) break;
            chineseDecimal.append(decimals[i] == 0 ? "" : (NUMBERS[decimals[i]] + DUNIT[i]));
        }
        return chineseDecimal.toString();
    }

    private static String getChineseInteger(int[] integers, boolean isMust5) {
        int len = integers.length;
        StringBuilder chineseInteger = new StringBuilder();
        for (int i = 0; i < len; i++) {
            // 0出现在关键位置：1234(万)5678(亿)9012(万)3456(元)
            // 特殊情况：10(拾元、壹拾元、壹拾万元、拾万元)
            String key = "";
            if (integers[i] == 0) {
                if ((len - i) == 13) // 万(亿)(必填)
                key = IUNIT[4];
                else if ((len - i) == 9) // 亿(必填)
                key = IUNIT[8];
                else if ((len - i) == 5 && isMust5) // 万(不必填)
                key = IUNIT[4];
                else if ((len - i) == 1) // 元(必填)
                key = IUNIT[0];
                // 0遇非0时补零，不包含最后一位
                if ((len - i) > 1 && integers[i + 1] != 0) key += NUMBERS[0];
            }
            chineseInteger.append(integers[i] == 0 ? key : (NUMBERS[integers[i]] + IUNIT[len - i - 1]));
        }
        return chineseInteger.toString();
    }

    private static boolean isMust5(String integerStr) {
        int len = integerStr.length();
        if (len < 4) {
            return false;
        } else {
            String subIntegerStr = "";
            if (len < 8) {
                subIntegerStr = integerStr.substring(0, len - 4);
            } else {
                subIntegerStr = integerStr.substring(len - 8, len - 4);
            }
            if (!subIntegerStr.equals("") && Integer.parseInt(subIntegerStr) > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static int[] toArray(String number) {
        int[] array = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            array[i] = Integer.parseInt(number.substring(i, i + 1));
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println(toChinese("1256.236"));
        System.out.println(toChinese("0.236"));
    }
}
