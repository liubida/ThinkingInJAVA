/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-8-31
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
public class tmp20110831 {
    static {
        System.out.println("hello");

    }

    public static void tm() {
        AA a = new BB();
        AA[] array = { new BB(), new BB(), new BB() };
        BB[] array_1 = new BB[5];
        System.out.println(array_1.length);
        array_1 = (BB[]) array;
        System.out.println(array_1.length);
        for (AA aa : array_1) {
            aa.p();
        }
    }

    public void tmp() {
    }

    tmp20110831() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        tm();
    }
}

class AA {
    void a(int i, String s) {
        System.out.println(i + "liubida");
    }

    void a(String s, int i) {
        System.out.println(i);
    }

    void p() {
        System.out.println("AA");
    }
}

class BB extends AA {
    BB() {
    }

    void p() {
        System.out.println("BB");
    }
    //    void a(String s, int i) {
    //        System.out.println(i+"zww");
    //    }
}
