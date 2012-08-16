/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-22
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

import java.util.Random;

/**
 * @author liubida
 */

class Cup {
    Cup(int marker) {
        System.out.println("Cup(" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}

class Cups {
    static Cup cup1;
    static Cup cup2;
    Cup        cup3;
    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }
    int[]      b = { 0, 1 };

    Cups() {
        cup3 = new Cup(3);
        System.out.println("Cups()");
    }
}

enum Spiciness {
    NOT,
    MILD,
    MEDIUM,
    HOT,
    FLAMING
}

class ArrayClass {
    Random      rand = new Random(610);
    Integer[]   a    = new Integer[rand.nextInt(80)];
    Integer[]   b    = new Integer[] { 1, 2, 3, 4 };
    Spiciness[] c    = Spiciness.values();

}

public class ExplicitStatic {
    public static void main(String[] args) {
    }

    static Cups cups1 = new Cups(); // (2)
    static Cups cups2 = new Cups(); // (2)
}
