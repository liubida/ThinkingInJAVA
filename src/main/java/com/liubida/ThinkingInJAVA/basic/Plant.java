/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-25
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
package com.liubida.ThinkingInJAVA.basic;

/**
 * @author liubida
 *
 */
public class Plant {

}

class Flower {
    int    petalCount = 0;
    String s          = "initial value";
    @Override
    public String toString(){
        return s+petalCount;
    }
    
    void growing(){
        System.out.println("112233");
    }
    
    Flower(int petals) {
        petalCount = petals;
        System.out.println("Constructor w/ int arg only, petalCount= " + petalCount);
    }

    Flower(String ss) {
        System.out.println("Constructor w/ String arg only, s = " + ss);
        s = ss;
    }

    Flower(String s, int petals) {
        this(petals);
        //this(s); // Can’t call two!
    }
}