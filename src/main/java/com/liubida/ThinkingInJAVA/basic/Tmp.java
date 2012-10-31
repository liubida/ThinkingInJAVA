/**
 * Project: ThinkingInJAVA
 * File Created at 2011-8-2
 * $Id$
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information"). You shall not
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

    private int i = -1;
    private AAATest a = new AAATest();
    private BBBTest b = new Tmp().new BBBTest();
    private Tmp t = new Tmp();
    private BBBTest c = t.new BBBTest();
}

public class Tmp {

    int t1 = 0;
    int t2 = 1;

    static class AAATest {};

    class BBBTest {};

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
        Map<String, Object> m1 = new HashMap<String, Object>();
        m.put("gender", 3);
        m.putAll(m1);
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

    public static void main2(String[] args) throws ParseException {
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

    public static void main(String[] args) {
        // System.out.println(Math.round(1.4));
        // System.out.println(Math.round(1.44));
        // System.out.println(Math.round(1.45));
        // System.out.println(Math.round(1.50));
        // System.out.println(Math.round(1.52));
        //
        // BigDecimal a = new BigDecimal(1.44);
        // BigDecimal b = new BigDecimal(1.49);
        // BigDecimal c = new BigDecimal(1.50);
        //
        // DecimalFormat f = new DecimalFormat("#0.00");
        // System.out.println(f.format(1.345)); // 五前为偶应舍去
        // System.out.println(f.format(1.3450001)); // 五后非零就进一
        // System.out.println(f.format(1.355)); // 五前为奇要进一

        // List<String> bbb = new ArrayList<String>();
        // bbb.add("123");
        // fff(bbb);
        // for (String string : bbb) {
        // System.out.println(string);
        // }
        //
        // Object a = new String("111");
        // System.out.println(null instanceof Integer);
        Map<String, Tmp> map = new HashMap<String, Tmp>();

        List<Tmp> l = new ArrayList<Tmp>();
        Tmp t1 = new Tmp();
        t1.t1 = 0;
        t1.t2 = 1;
        Tmp t2 = new Tmp();
        t2.t1 = 2;
        t2.t2 = 3;
        l.add(t1);
        l.add(t2);
        map.put("0", l.get(0));
        map.put("1", l.get(1));
        for (String s : map.keySet()) {
            System.out.println(map.get(s).t1);
            System.out.println(map.get(s).t2);
        }
        l.get(0).t1=100 ;
        for (String s : map.keySet()) {
            System.out.println(map.get(s).t1);
            System.out.println(map.get(s).t2);
        }
        
        

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("appName", 1);
        m.put("gender", 2);
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("gender", 3);
        m.putAll(m1);
        System.out.println(m.get("gender"));
        System.out.println(m.get("gender"));
        System.out.println(m.get("gender"));
        System.out.println(m.get("gender"));
    }

    public static void fff(List<String> a) {
        a.add("11111");
        a.add("11111");
        a.add("11111");
        a.add("11111");
        a.remove(0);
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
