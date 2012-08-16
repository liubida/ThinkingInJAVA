/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-20
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
import static com.liubida.ThinkingInJAVA.util.Print.print;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface SomeMethods {
    void boring1();

    void boring2();

    void interesting(int i);
}

class SomeImpls implements SomeMethods {
    @Override
    public void boring1() {
        print("boring 1");
    }

    @Override
    public void boring2() {
        print("boring 2");
    }

    @Override
    public void interesting(int i) {
        print("interesting int:" + i);
    }
}

class MethodFilter implements InvocationHandler {
    private Object proxied;

    public MethodFilter(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().contains("interesting")) {
            print("this is a interesting method");
            return method.invoke(proxied, (Integer)args[0]+7);
        } else {
            return method.invoke(proxied, args);
        }
    }
}

public class SelectingMethods {
    public static void main(String[] args) {
        SomeMethods m = (SomeMethods)Proxy.newProxyInstance(SomeMethods.class.getClassLoader(),
                new Class[] { SomeMethods.class }, new MethodFilter(new SomeImpls()));
        m.boring1();
        m.boring2();
        m.interesting(10);
    }
}
