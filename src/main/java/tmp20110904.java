/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-9-4
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
public class tmp20110904 {
    public static void main(String[] args) {
        new B1();
    }
}

class A1 {
    B1 b = new B1();

    void a() {
    };
}

class B1 {
    A1 a = new A1();

    void a() {
    };
}
