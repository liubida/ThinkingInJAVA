/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-8-2
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
package com.liubida.ThinkingInJAVA.basic;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liubida.ThinkingInJAVA.basic.Tmp.AAATest;
import com.liubida.ThinkingInJAVA.basic.Tmp.BBBTest;

/**
 * @author
 */

class aa {

    private int     i = -1;
    private AAATest a = new AAATest();
    private BBBTest b = new Tmp().new BBBTest();
    private Tmp     t = new Tmp();
    private BBBTest c = t.new BBBTest();
}

public class Tmp {

    static class AAATest {
    };

    class BBBTest {
    };

    public static void main1(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException,
                                           IllegalAccessException {
        String create_table_server = "create table server (id integer primary key, " + "machineRoomId integer,"
                                     + "logicSiteId integer ," + "cabinetId integer ," + "deviceModelId integer ,"
                                     + "deparmentId integer ," + "serviceTag text not null," + "comments text ,"
                                     + "hostname text ," + "ip text ," + "cabinetPositionNum text ,"
                                     + "cabinetSlotNum text ," + "manageIp text ,"
                                     + "responsibilityPersonUserId integer ,"
                                     + "secondResponsibilityPersonUserId integer ," + "useState text )";
        System.out.println(create_table_server);

        // String[] p = "1".split("-");
        // System.out.println(p[0]);
        // System.out.println(p[1]);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("appName", 1);
        m.put("gender", 2);
        System.out.println(m.get("gender"));
        System.out.println(m.get("appName"));
        System.out.println(m.get("appName111"));
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();

        System.out.println(sf.format(now));

        aa test = new aa();
        Field i = aa.class.getDeclaredField("i");
        System.out.println(i.isAccessible());
        if (!i.isAccessible()) {
            i.setAccessible(true);
        }
        System.out.println(i.getInt(test));

    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str3 = "1937-12-31 23:54:07";
        String str4 = "1937-12-31 23:54:08";
        Date sDt3 = sf.parse(str3);
        Date sDt4 = sf.parse(str4);

        long ld3 = sDt3.getTime() / 1000;
        long ld4 = sDt4.getTime() / 1000;
        System.out.println(ld3);
        System.out.println(ld4);
        System.out.println(ld4 - ld3);

        System.out.println("-----------------");
        toCharacter("102");
        toCharacter("DC13");
        toCharacter("no");
        toCharacter("仓库");

        List<Integer> aList = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            aList.add(i);
        }
        for (int i = 0; i < aList.size(); i++) {
            if (4 == i || 5 == i) {
                aList.remove(i);
                System.out.println(aList.get(i));
            }
        }
        System.out.println(aList.get(4));
        System.out.println(aList.get(5));
        for (int i = 0; i < aList.size(); i++) {
            // System.out.println(aList.get(i));
        }

        byte bytes = -42;
        int result = bytes & 0xff;
        int r = bytes;
        System.out.println("无符号数: \t" + result);
        System.out.println("无符号数: \t" + r);
        System.out.println("2进制bit位: \t" + Integer.toBinaryString(result));

        Map<String, Integer> m = new HashMap<String, Integer>();
        m.put("liubida", 2);
        m.put("zww", 3);
        m.put("3rt", 4);
        m.put("fefe", 5);
        System.out.println(m.get(null) == null);
    }

    public static void toCharacter(String s) {
        // char[] array = s.toCharArray();
        // String to = String.copyValueOf(array, 0, 2);
        // System.out.print(to);

        // System.out.print(array[1]);
        // System.out.print(array[2]);
        // for (char c : s.toCharArray()) {
        // System.out.print(c);
        // System.out.print(' ');
        // }
        // System.out.println();
    }
}

class A {

    public A(int i){
    }

    public A(){
        System.out.println('a');
    }
}

class B extends A {

    public B(int i){
    }

    protected void finalize() {
        try {
            super.finalize();
        } catch (Throwable e) {
        }
    }
}

class SomeClass {

    private static SomeClass s = null;

    private SomeClass(){
    }

    static SomeClass getSomeClass() {
        if (null == s) {
            synchronized (SomeClass.class) {
                if (null == s) {
                    s = new SomeClass();
                }
            }
        }
        return s;
    }
}

class SomeClass2 {

    private SomeClass2(){
    }

    private static class instanceHolder {

        static SomeClass2 instance = new SomeClass2();
    }

    static SomeClass2 getSomeClass2() {
        return instanceHolder.instance;
    }
}
