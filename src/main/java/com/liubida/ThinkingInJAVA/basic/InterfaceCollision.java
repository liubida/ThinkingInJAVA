/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-27
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
 * 
 * @author liubida
 */

interface I1 {
    void f();
}

interface I2 {
    int f(int i);
}

interface I3 {
    int f();
}

class C {
    public int f() {
        return 1;
    }
}

class C2 implements I1, I2 {
    public void f() {
    }

    public int f(int i) {
        return 1;
    } // overloaded
}

class C3 extends C implements I2 {
    public int f(int i) {
        return 1;
    } // overloaded
}

class C4 extends C implements I3 {
    // Identical, no problem:
    public int f() {
        return 1;
    }
}

// Methods differ only by return type:
/**
 * C5继承了C，则C5就有了int f()这个method，那么就有两点：
 * 1. C5的int f() 和 I1的void f()冲突
 * 2. I1的void f()在C5类中没有被实现
 */
//class C5 extends C implements I1 {}
/**
 * I4同时继承I1和I3，那么I4同时含有I1和I3的方法定义，
 * 而I1的void f() 和 I3的int f()冲突
 * 
 * 注：只有接口的多继承才能用extends后面连上多个接口的用法
 *    类只能有一个extends的类，但是可以implements多个接口
 */
//interface I4 extends I1, I3 {} ///:~

public class InterfaceCollision {

}
