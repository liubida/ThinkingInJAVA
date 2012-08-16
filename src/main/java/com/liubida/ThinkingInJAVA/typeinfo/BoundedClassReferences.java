/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-18
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
package com.liubida.ThinkingInJAVA.typeinfo;

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.print;

import java.lang.reflect.Field;

class Human {
}

class Man extends Human {
    public void m(){
        print("m");
    }
}

class Woman extends Human {
    public void w(){
        print("w");
    }
}

public class BoundedClassReferences {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Class<? extends Human> p = Human.class;
        print(p.getName());
        p = Man.class;
        print(p.getName());        
        Man man = (Man) p.newInstance();
        man.m();
        p = Woman.class;
        print(p.getName());
        
        Integer i = new Integer(1);
        Class ic = i.getClass();
        Field[] f = ic.getDeclaredFields();
        for (Field field : f) {
            print(field.getName());
        }
    }
}
