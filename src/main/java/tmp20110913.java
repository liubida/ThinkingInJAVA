/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-9-13
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
public class tmp20110913 {
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {
        String s1 = "123";
        s1 = s1.concat("4");
        System.out.println(s1);

        System.out.println(A0913.s);
        System.out.println(((A0913) new B0913()).s);

        Object[][] array = new Object[3][];
        array[0] = new String[2];
        array[1] = new String[3];
        array[2] = new String[4];
        array[0][0] = "liubida";
        array[0][1] = "liubida1";
        array[1][0] = "liubida1";
        new A0913().foo();
        StringBuilder a = new StringBuilder();
        StringBuffer b = new StringBuffer();
    }
}

class A0913 {
    static String s = "aaa";

    void foo() throws Exception {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new Exception("exception in catch" + e.getMessage());
        } finally {
            //            throw new Exception("exception in finally");
        }
    }

    final void fun1(int i, String s) {
    }

    final void fun1(String s, int i) {

    }
}

class B0913 extends A0913 {
    static String s = "bbb";
}

class C0913 {
    String s;

    C0913() {
        s = "123";
    }
}

interface D0913 {
}
