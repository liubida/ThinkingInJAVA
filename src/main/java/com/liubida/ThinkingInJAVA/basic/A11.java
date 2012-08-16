/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-9-4
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author liubida
 */
public class A11 {

    private static final String TAG = "ArmoryUtil";

    public static Map<String, String> getDBMap(Object o) {
        Map<String, String> map = new HashMap<String, String>();
        Field[] fields = o.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                Object tmp = f.get(o);
                map.put(f.getName(), tmp == null ? "" : tmp.toString());
            }
        } catch (Exception e) {
            System.out.println("getDBMap is error: " + e.getMessage());
        }
        return map;
    }

    public static void main(String[] args) {
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Set<Map<String,Object>> s = new HashSet<Map<String, Object>>();
        m1.put("a", 1);
        m2.put("a", 1);
        s.add(m1);
        s.add(m2);
        for (Map<String, Object> map : s) {
            System.out.println(map.get("a"));
        }
    }
}
