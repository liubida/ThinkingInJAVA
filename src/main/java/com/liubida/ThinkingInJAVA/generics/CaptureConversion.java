/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-2
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
package com.liubida.ThinkingInJAVA.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liubida
 */
public class CaptureConversion {
    static <T> void f1(List<T> list) {
        T t = list.get(0);
        System.out.println(t.getClass().getSimpleName());
    }
    static <T> void f2(List<?> list){
        f1(list);
    }
    public static void main(String[] args) {
        List a = new ArrayList(1);
        a.add(1);
        f1(a);f2(a);
        
        List b= new ArrayList<Double>();
        b.add(0.2);
        f1(b);f2(b);
        
        List<?> c = new ArrayList<String>();
//        c.add("liubida");
//        f1(c);f2(c);
        byte aa = (byte) -384;
        System.out.println(aa);
        
    }
}
