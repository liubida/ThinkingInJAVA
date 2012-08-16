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
import static com.liubida.ThinkingInJAVA.util.Print.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Boy {
    void doSomething();
    void somethingElse(String arg);
}

class RealBoy implements Boy {
    @Override
    public void doSomething() {
        print("liubida");
    }

    @Override
    public void somethingElse(String arg) {
        print("liubida love " + arg);
    }

}

class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() + ", method: " + method + ", args: "
                + args);
        return method.invoke(proxied, args);
    }
}

public class SimpleDynamicProxy {
    public static void main(String[] args) {
        RealBoy real = new RealBoy();
        Boy proxy = (Boy)Proxy.newProxyInstance(Boy.class.getClassLoader(),
                new Class[] { Boy.class }, new DynamicProxyHandler(real));
        /**
         * here, any operations on proxy, like "iface.doSomething()", it forward the 
         * request to the proxied object(real)
         * same way to the "iface.somethingElse("zww")" 
         */
        proxy.doSomething();
        proxy.somethingElse("zww");
    }
}
