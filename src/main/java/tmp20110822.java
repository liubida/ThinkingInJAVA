/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-8-22
 * $Id$
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */

/**
 * @author liubida
 */
public class tmp20110822 {
    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++)
            System.out.println(MultipleReturns.f(i));
        System.out.println("-----------------");
        for (int i = 1; i <= 4; i++)
            System.out.println(MultipleReturns.g(String.valueOf(i)));
        System.out.println("-----------------");
        for (int i = 1; i <= 4; i++)
            System.out.println(MultipleReturns.h(i));
        System.out.println("-----------------");
        for (int i = 1; i <= 4; i++)
            System.out.println(MultipleReturns.m(i));
    }
}

class MA {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}

@SuppressWarnings("finally")
class MultipleReturns {
    public static int f(int i) {
        try {
            if (i == 1)
                return 1;
            if (i == 2)
                return 2;
            if (i == 3)
                return 3;
            else
                return 0;
        } finally {
            return -1;
        }
    }

    public static MA h(int i) {
        MA a = new MA();
        try {
            if (i == 1) {
                a.setValue(1);
                return a;
            }
            if (i == 2) {
                a.setValue(2);
                return a;
            }
            if (i == 3) {
                a.setValue(3);
                return a;
            } else {
                //                a.setValue(0);
                //                return a;
            }
        } finally {
            a.setValue(-1);
        }
        a.setValue(-2);
        return a;
    }

    public static int m(int i) {
        int a;
        try {
            if (i == 1) {
                a = 1;
                return a;
            }
            if (i == 2) {
                a = 2;
                return a;
            }
            if (i == 3) {
                a = 3;
                return a;
            } else {
                a = 0;
                return a;
            }
        } finally {
            a = -1;
        }
    }

    public static String g(String i) {
        try {
            if (i.equals("1"))
                return "1";
            if (i.equals("2"))
                return "2";
            if (i.equals("3"))
                return "3";
            else
                return "0";
        } finally {
            return "-1";
        }
    }

}
