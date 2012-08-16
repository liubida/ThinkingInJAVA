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
 * Singleton模式主要作用是保证在Java应用程序中，一个类Class只有一个实例存在
 * 
 * @author liubida
 */

class Singleton {
    private Singleton() {
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}

class Singleton2 {
    private Singleton2() {
    }

    private static Singleton2 instance = null;

    public static synchronized Singleton2 getInstance() {
        if (null == instance) {
            instance = new Singleton2();
        }
        return instance;
    }

}
