

/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-9-17
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
 */
public class tmp20110917 {
    public static void main(String[] args) {
        A0917 a = new A0917();
        B0917 b = new B0917();
        C0913 c = new C0913();
        System.out.println(a.s == b.s);
        System.out.println(a.s == c.s);
        
        String s = "123";
        String s1 = new String("123");
        String s2 = s1.intern();
    }
}

class A0917 {
    String s;

    A0917() {
        s = "123";
    }
}

class B0917 {
    String s;

    B0917() {
        s = "123";
    }
}
