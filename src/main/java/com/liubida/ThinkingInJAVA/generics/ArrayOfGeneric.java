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

class Generic<T>{
}
class ArrayOfGenericRef{
    static Generic<Integer>[] gia;
}

public class ArrayOfGeneric {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;
    public static void main(String[] args) {
        gia = new Generic[SIZE]; // equal: (Generic<Integer>[])new Generic[SIZE];
        System.out.println(gia.getClass().getSimpleName());
        gia[0] = new Generic<Integer>();
        gia[1] = new Generic<Integer>();
        System.out.println(gia[0].getClass().getSimpleName());
        System.out.println(gia[1].getClass().getSimpleName());
    }
}
