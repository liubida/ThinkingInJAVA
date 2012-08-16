/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-1
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
 *
 */
public class GenericWriting {
    static List<Fruit> fruits = new ArrayList<Fruit>();
    static List<Apple> apples = new ArrayList<Apple>();
    
    static <T> void writeExact(List<T> list, T item){
        list.add(item);
    }
    static void writeFruit(List<Fruit> list, Fruit item){
        list.add(item);
    }
    static void f1() {
        writeExact(apples, new Apple());
         writeExact(fruits, new Apple()); // Error:
        // Incompatible types: found Fruit, required Apple
        }
//    static <T> void writeWithWildcardExtends(List<? extends T> list, T item){
//        list.add(item);
//    }
    static <T> void writeWithWildcardSuper(List<? super T> list, T item){
        list.add(item);
    }
    public static void main(String[] args) {
        writeExact(fruits, new Fruit());
        writeExact(fruits, new Apple());
//        writeExact(apples, new Fruit());
        writeWithWildcardSuper(fruits, new Apple());
        System.out.println(fruits);
    }
}
