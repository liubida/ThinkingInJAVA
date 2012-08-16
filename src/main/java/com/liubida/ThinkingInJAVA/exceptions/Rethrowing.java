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
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;

public class Rethrowing {
    static void f() throws Exception{
        throw new Exception("from f");
    }
    static void g() throws Exception{
        try {
            f();
        } catch (Exception e) {
            throw e;
        }
    }
    static void h() throws Exception{
        try {
            f();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    static void i() throws Exception{
        try {
            f();
        } catch (Exception e) {
            throw (Exception)e.fillInStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try {
            g();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        try {
            h();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            i();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
