/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-31
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

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.print;


import java.util.HashMap;
import java.util.Map;

import com.liubida.ThinkingInJAVA.generics.coffee.Coffee;
import com.liubida.ThinkingInJAVA.generics.coffee.Latte;

class Building{}
class House extends Building{}

public class ClassTypeCapture<T> {
    Class<T> type;
    public Map<String, Class<?>> kindMap = new HashMap<String, Class<?>>();
    public ClassTypeCapture(Class<T> type){
        this.type = type;
    }
    public void addType(String typeName, Class<?> kind){
        kindMap.put(typeName, kind);
    }
    public Object createNewObject(String typeName){
        try {
            return kindMap.get(typeName).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Class<?> createNewClass(String typeName){
        try {
            return kindMap.get(typeName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean f(Object arg){
        return type.isInstance(arg);
    }
    public static <T> ClassTypeCapture<T> generator(Class<T> type){
        return new ClassTypeCapture<T>(type);
    }
    public static void main(String[] args) {
        ClassTypeCapture<Building> c = generator(Building.class);
        ClassTypeCapture<House> d = generator(House.class);
        print(c.f(new House()));    // up-cast is automatic
//        print(d.f((House)new Building())); 
        print("---------------------------------");
        c.addType("String", new String("nihao").getClass());
        c.addType("Coffee", Coffee.class);
        c.addType("Latte", Latte.class);
//        print(c.kindMap.get("String"));
//        print(c.kindMap.get("Coffee"));
        print(c.createNewClass("Coffee"));
        print(c.createNewObject("Coffee"));
        print(c.createNewClass("Latte"));
        print(c.createNewObject("Latte"));
        
    }
}
