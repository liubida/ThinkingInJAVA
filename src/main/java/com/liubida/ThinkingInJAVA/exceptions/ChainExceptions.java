/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-3
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
 * 
 * @author liubida
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;
@SuppressWarnings("serial")
class AException extends Exception {
}

public class ChainExceptions {
    static void f() throws Exception {
        throw new Exception("from f");
    }

    static void g() throws Exception {
        try {
            f();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    static void h() throws Exception {
        try {
            f();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static void i() throws Exception{
        try {
            f();
        } catch (Exception e) {
            AException a = new AException();
            a.initCause(e);
            throw a;
        }
    }
    
    public static void main(String[] args) {
        try {
            g();
        } catch (Exception e) {
            e.printStackTrace();
            print("----------------------\n");
        }
        try {
            h();
        } catch (Exception e) {
            e.printStackTrace();
            print("----------------------\n");
        }
        try {
            i();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
