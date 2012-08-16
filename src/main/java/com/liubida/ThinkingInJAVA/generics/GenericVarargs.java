/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-30
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
import java.util.Arrays;
import java.util.List;

/**
 * @author liubida
 */
public class GenericVarargs {
    public static <T> List<T> makeList(T... args) {
        List<T> ret = new ArrayList<T>();
        for (T t : args) {
            ret.add(t);
        }
        return ret;
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println(makeList("A"));
        System.out.println(makeList(Arrays.asList("11","22","33","44")));
        System.out.println(makeList(new String[]{"11","22","33","44"}));
        System.out.println(makeList("abcdefghijklmn".split("")));
        System.out.println(makeList(new Integer[]{1,2,3,4}));
    }
}
