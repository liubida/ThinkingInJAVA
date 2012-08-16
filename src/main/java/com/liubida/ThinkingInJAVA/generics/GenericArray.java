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
public class GenericArray<T> {
    private T[] array;
    public GenericArray(int n){
        array = (T[])new Object[n];
    }
    public void put(int index, T item){
        array[index] = item;
    }
    public T get(int index){
        return array[index];
    }
    public T[] rep(){
        return array;
    }
    public static void main(String[] args) {
        GenericArray<Integer> g = new GenericArray<Integer>(8);
        Object[] a = g.rep();
    }
}
