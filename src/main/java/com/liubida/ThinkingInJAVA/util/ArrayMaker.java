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


import java.lang.reflect.Array;
import java.util.Arrays;

import com.liubida.ThinkingInJAVA.generics.coffee.Coffee;
import com.liubida.ThinkingInJAVA.generics.coffee.CoffeeGenerator;


/**
 * @author liubida
 */
public class ArrayMaker<T> {
    private T[] array;

    @SuppressWarnings("unchecked")
    public ArrayMaker(Class<T> type, int sz) {
        array = (T[]) Array.newInstance(type, sz);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

    public static <T> ArrayMaker<T> generator(Class<T> type, int n) {
        return new ArrayMaker<T>(type, n);
    }

    public static void main(String[] args) {
        ArrayMaker<Integer> m = new ArrayMaker<Integer>(Integer.class, 8);
        ArrayMaker<Coffee> mc = ArrayMaker.generator(Coffee.class, 8);
        for (int i = 0; i < 8; i++) {
            m.put(i, i*10);
            mc.put(i, new CoffeeGenerator().next());
        }
        System.out.println(Arrays.toString(m.rep()));
        System.out.println(Arrays.toString(mc.rep()));
        int[] a = (int[]) Array.newInstance(int.class, 10);
        System.out.println(Arrays.toString(a));
    }
}
