/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-5
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
package com.liubida.ThinkingInJAVA.exceptions;

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;

class A {
    private static long count = 1;
    private final long  id    = count++;

    public void dispose() {
        print("A :" + id + " disposed");
    }
}

class AA extends A {
    public AA() throws Exception {
    }
}

public class CleanupIdiom {
    public static void main(String[] args) {
        /**
         * 如果构造函数不可能失败, 则只需要在finally里执行dispose就可以了 注意顺序相反
         */
        A a1 = new A();
        A a2 = new A();
        try {
            //...
        } finally {
            a2.dispose();
            a1.dispose();
        }

        /**
         * 如果构造函数有可能失败，则要依次用try-catch包裹，最后再加上finally
         * 如果dispose也有可能抛异常，则也要用try-catch包裹
         */
        try {
            A a3 = new AA();
            try {
                A a4 = new AA();
                try {
                    //...
                } finally {
                    a4.dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                a3.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
