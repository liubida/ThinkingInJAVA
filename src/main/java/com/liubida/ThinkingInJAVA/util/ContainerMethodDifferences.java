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

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.liubida.ThinkingInJAVA.util.Sets;

import static com.liubida.ThinkingInJAVA.util.Print.*;

/**
 * @author liubida
 */
public class ContainerMethodDifferences {
    static <T> Set<String> methodSet(Class<T> type) {
        Set<String> ret = new HashSet<String>();
        for (Method m : type.getMethods()) {
            ret.add(m.getName());
        }
        return ret;
    }

    static Set<String> difference(Class<?> Super, Class<?> Sub) {
        print(Sub.getSimpleName() + " extends " + Super.getSimpleName() + ", difference: ");
        return Sets.difference(methodSet(Sub), methodSet(Super));
    }

    public static void main(String[] args) {
        print("Collection: " + methodSet(Collection.class));
        print("Set: " + methodSet(Set.class));
        print("TreeSet: " + methodSet(TreeSet.class));
        print(difference(Collection.class, Set.class));
        print(difference(Set.class, TreeSet.class));
    }
}
