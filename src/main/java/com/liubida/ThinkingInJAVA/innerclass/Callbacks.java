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
import static com.liubida.ThinkingInJAVA.util.Print.*;

interface Incrementable {
    void increment();
}

class Add {
    public void increment() {
        print("i'm not doing the inc");
    }
}

class Callee1 implements Incrementable {
    private int i = 0;

    public void increment() {
        print(++i);
    }
}

class Callee2 extends Add {
    private int i = 0;

    public void increment() {
        super.increment();
        print(++i);
    }

    private class Closure implements Incrementable {
        public void increment() {
            Callee2.this.increment();
        }
    }

    Incrementable getCallbackReference() {
        return new Closure();
    }

    //    static class testMain{
    //        public static void main(String[] args) {
    //            Callee2 a = new Callee2();
    //            a.increment();
    //        }
    //    }
}

public class Callbacks {
    public static void main(String[] args) {

    }
}
