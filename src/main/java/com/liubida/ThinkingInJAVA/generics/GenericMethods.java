/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-27
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

public class GenericMethods {
    public <T> T f(T v){
        System.out.println(v.getClass().getSimpleName());
        return v;
    }
    public Object g(Object o){
        System.out.println(o.getClass().getSimpleName());
        return o;
    }
    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("1");
        gm.f(1);gm.f(1f);
        gm.g("2");
        gm.f(2);gm.f(2f);
    }
}
