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
 */

import static com.liubida.ThinkingInJAVA.util.Print.*;

class MyException extends Exception {

}
class Exception3 extends MyException{
    
}
class Exception2 extends Exception3{
    
}
class Exception1 extends Exception2{
    
}

class Test {
    static int count = 0;

    static int test() {
        count = 0;
        while (true) {
            try {
                throw new MyException();
            } catch (MyException e) {
                print("caught a exception:" + count);
            } finally {
                print("in test:" + count++);
                if (5 == count) {
                    break;
                }
            }
        }
        return count;
    }

    @SuppressWarnings("finally")
    static int run() {
        count = 0;
        while (true) {
            try {
                count++;
                throw new MyException();
            } catch (MyException e) {
                print("caught a exception:" + count);
                return -1;
            } finally {
                return count;
            }
        }
    }

    static Integer on() {
        count = 0;
        int a = 99;
        Integer b = new Integer(99);
        while (true) {
            try {
                throw new MyException();
            } catch (MyException e) {
                print("caught a exception:" + count);
                return b;
            } finally {
                //                print(count++);
                b = new Integer(-100);
//                                return b;
                //                if (5 == count) {
                //                    return count;
                //                }
            }
        }
    }
}

public class FinallyWorks {
    public static void main(String[] args) {
        //        print(Test.test());
        //        print(Test.run());
        print(Test.on());
    }
}
