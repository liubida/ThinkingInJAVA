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
package com.liubida.ThinkingInJAVA.util;

import java.util.HashSet;
import java.util.Set;

import static com.liubida.ThinkingInJAVA.util.Print.*;
/**
 * @author liubida
 */
public class Sets {
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> ret = new HashSet<T>(a);
        ret.addAll(b);
        return ret;
    }

    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> ret = new HashSet<T>(a);
        ret.retainAll(b);
        return ret;
    }

    public static <T> Set<T> difference(Set<T> superSet, Set<T> subSet) {
        Set<T> ret = new HashSet<T>(superSet);
        ret.removeAll(subSet);
        return ret;
    }

    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }
}
