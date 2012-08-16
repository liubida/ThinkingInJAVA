/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-28
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

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;

class Out{
    class In{
        Out getOut(){
            return Out.this;
        }
    }
    static class anotherIn{
        /**
         * static 修饰 class，只有静态内部类的情况
         * @return
         */
        void p(){
            print("i'm antherIn");
        }
    }
//    In in(){return new In();}
    void f(){print("i'm out");};
}

class InnerClass {
    public static void main(String[] args) {
        Out o = new Out();
        Out.In i = o.new In();  
        /**
         * error: = new Out.In()   -> It’s not possible to create an object of the inner class 
         *                            unless you already have an object of the outer class
         * error: = o.new Out.In() -> 命名范围重复
         * ok:    = o.in()         -> 不用new关键字，调用自己写的创建In的方法
         */
        i.getOut().f();
    }
}



