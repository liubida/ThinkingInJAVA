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
package com.liubida.ThinkingInJAVA.innerclass;

/**
 * @author liubida
 *
 */

/**
 * X和Y同时继承A，B接口以及C抽象类的两种方法
 * 这两种方式没有什么区别
 * 
 */
interface A{
    void a();
}
interface B{
    void b();
}
abstract class C{
    abstract void c();
}
class X extends C implements A, B{
    public void a(){}
    public void b(){}
    void c(){}
}
class Y implements A{
    public void a(){}
    class YIN extends C implements B{
        public void b(){}
        void c(){}
    }
}

/**
 * 但是要同时继承多个类，或者同时继承多个类和抽象类
 * 就只能用inner class的方式
 */

abstract class D{
    abstract void d();
}
class E{
    void e(){}
}
class F{
    void f(){}
}

class Z extends D{
    void d(){}
    class ZIN1 extends E{
        
    }
    class ZIN2 extends F{
        
    }
    static class testMain{
        public static void main(String[] args) {
            Z z = new Z();
            ZIN1 zin1 = z.new ZIN1();
            ZIN2 zin2 = z.new ZIN2();
            zin1.e();
            zin2.f();
        }
    }
}