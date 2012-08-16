package com.liubida.ThinkingInJAVA.basic;
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

/**
 * @author liubida
 *
 */
class Cookie {
    public Cookie() {
        System.out.println("Cookie constructor");
    }
    void bite() {
        System.out.println("bite");
    }
    void a(int i){
        System.out.println("Cookie a int");
    }
    void a(float i){
        System.out.println("Cookie a float");
    }
}

class ChocolateChip extends Cookie {
    public ChocolateChip() {
        System.out.println("ChocolateChip constructor");
    }

    public void chomp() {
        bite();
    }
    @Override
    void a(int i){
        System.out.println("ChocolateChip a int");
    }
}

public class ExtendsAndOverriden {
    public static void main(String[] args) {
        ChocolateChip x = new ChocolateChip();
        x.chomp();
        x.a(0);
    }
}
