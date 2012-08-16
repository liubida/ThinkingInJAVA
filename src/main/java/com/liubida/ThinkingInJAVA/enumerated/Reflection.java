/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-17
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
package com.liubida.ThinkingInJAVA.enumerated;

import static com.liubida.ThinkingInJAVA.util.Print.print;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

import com.liubida.ThinkingInJAVA.util.OSExecute;


/**
 * @author liubida
 */
enum Explore {
    HERE,
    THERE
}

public class Reflection {
    public static Set<String> analyze(Class<?> enumClass) {
        print(enumClass.getName());print("interface");
        for (Type t : enumClass.getGenericInterfaces()) {
            print(t);
        }
        print("methods");
        Set<String> methods = new TreeSet<String>();
        for (Method m : enumClass.getMethods()) {
            methods.add(m.getName());
        }
        print(methods);
        return methods;
    }

    public static void main(String[] args) {
        analyze(Explore.class);
        print("-------------");
        analyze(Enum.class);
        print("-------------");
        Explore a = Explore.HERE;
        for (Explore e : a.values()) {
            print(e);
        }
//        OSExecute.command("javap Reflection");
//        print(exploreMethods);
//        print(enumMethods);
    }
}
