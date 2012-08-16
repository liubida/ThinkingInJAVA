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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liubida
 */
public class UnboundedWildCard {
    static Map            map1;
    static Map<?, ?>      map2;
    static Map<String, ?> map3;
    static Map<?, String> map4;

    static void assign1(Map map) {
        map1 = map;
    }

    static void assign2(Map<?, ?> map) {
        map2 = map;
    }

    static void assign3(Map<String, ?> map) {
        map3 = map;
    }

    static void assign4(Map<?, String> map) {
        map4 = map;
    }

    public static void main(String[] args) {
        List<?> l =new ArrayList<Integer>();
//        l.add(Integer.valueOf(10));
        //        assign1(new HashMap());
        //        assign2(new HashMap());
        //        assign3(new HashMap());
        //        assign4(new HashMap());

        for (int i = 0; i < 6; i++) {
            UnboundedWildCard.map1.put(String.valueOf(i), i);
        }

        assign1(new HashMap());
        assign2(new HashMap<Integer, String>());
        //        assign3(new HashMap<Integer,String>());
        for (int i = 0; i < 6; i++) {
//            UnboundedWildCard.map2.put(Integer.valueOf(i), String.valueOf(i));
        }

        assign1(new HashMap<String, Integer>());
        assign2(new HashMap<String, Integer>());
        assign3(new HashMap<String, Integer>());
        for (int i = 0; i < 6; i++) {
//            UnboundedWildCard.map3.put(String.valueOf(i), Integer.valueOf(i));
        }
    }
}
