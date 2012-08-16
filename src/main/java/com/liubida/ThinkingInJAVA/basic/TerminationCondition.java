/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-20
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
import static com.liubida.ThinkingInJAVA.util.Print.print;
import static com.liubida.ThinkingInJAVA.util.Range.range;

/**
 * @author liubida
 */

class Book {
    static int finalCount = 0;
    Flower a = new Flower(0);
    
    int b = 1;
    Flower c;
    public void print(){ 
        a.growing();
        System.out.println(a.toString());
        System.out.println(b);
        System.out.println(c);
        range(5);
    }
    protected void finalize() {
        System.out.println("FinalCount: " + ++finalCount);
        // Normally, you’ll also do this:
        // super.finalize(); // Call the base-class version
    }
}
class TerminationCondition {
    public static void main(String[] args) {
        Book novel = new Book();
        // Drop the reference, forget to clean up:
        new Book();new Book();new Book();new Book();
        // Force garbage collection & finalization:
        novel.print();
        System.gc();
    }
}


class OverloadingOrder {
    static void f(String s, int i) {
        System.out.println("String: " + s + ", int: " + i);
    }

    static void f(int i, String s) {
        System.out.println("int: " + i + ", String: " + s);
    }

    public static void main2(String[] args) {
        f("String first", 11);
        f(99, "Int first");
    }
}

class Operators {
    /**
     * @param args
     */
    public static void main1(String[] args) {
        byte i = -1;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i >>> 10));
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i >>>= 10));
        System.out.println(Integer.toBinaryString(i >>> 10));

        int x = 1, y = 2, z = 3;
        String s = x + y + z + "" + x + y + z + "->";
        System.out.println(s + x + y + z);
        print(s + (x + y + z));

        //        while(x=y){
        //            System.out.println("liubida");
        //        }
        float a = 0.4f;
        double b = 0.8;

        System.out.println((int) a);
        System.out.println((int) b);
        System.out.println(Math.round(a));
        System.out.println(Math.round(b));
        

    }

}
