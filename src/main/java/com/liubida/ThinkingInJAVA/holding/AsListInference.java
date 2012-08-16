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
package com.liubida.ThinkingInJAVA.holding;

/**
 * @author liubida
 *
 */
import java.util.*;

/**
 * @author liubida A / \ B C |\ D E
 */
class A {
}

class B extends A {
}

class C extends A {
}

class D extends B {
}

class E extends B {
}

@SuppressWarnings("unused")
public class AsListInference {
    public static void main(String[] args) {
        //    Object m = Arrays.asList(b);
        //    Object n = Arrays.asList(b,c);

        List<A> x = Arrays.<A> asList(new B()); // 为什么这一行需要有<A>的强制转换？它和下一行的区别是什么？
        List<A> y = Arrays.asList(new B(), new C(), new D());

        List<A> z = Arrays.<A> asList(new D(), new E());
        List<B> n = Arrays.asList(new D(), new E());

        List<A> l = new ArrayList<A>();
        Collections.addAll(l, new B(), new C());
        
    }
}
