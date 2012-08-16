/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.generics;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author liubida 2012-2-27 上午9:36:17
 */

public class Gener<T> {
    private T data = null;
    
    void print(T t) {
        System.out.println("i'm parent: " + t);
    }

    public static void main(String[] args) {
//        Gener g = new SonGener();
//        g.print(1);
        Gener<List> g1 = new Gener<List>();
        Gener<Set> g2 = new Gener<Set>();
        System.out.println(g1.getClass().getName());
        System.out.println(g2.getClass().getName());
    }
}

//class SonGener extends Gener<String> {
//
//    void print(String t) {
//        System.out.println("i'm son: " + t);
//    }
//
//}
