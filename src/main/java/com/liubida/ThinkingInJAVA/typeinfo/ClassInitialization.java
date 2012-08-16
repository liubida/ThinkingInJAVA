/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-18
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
package com.liubida.ThinkingInJAVA.typeinfo;

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;

class Initable {
    static final int staticFinalInt = 1;
    static int       staticInt      = 2;
    static {
        print("i'm loading...");
    }
}

public class ClassInitialization {
public static void main(String[] args) {
    Class i = Initable.class;
    print(Initable.staticFinalInt);
    print(Initable.staticInt);
    print(i.getClass());
}
}
