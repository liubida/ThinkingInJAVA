/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-10-16
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
package com.liubida.ThinkingInJAVA.concurrency;

/**
 * @author liubida
 */
public class FibonacciTask implements Runnable {
    private int num;

    public FibonacciTask(int num) {
        this.num = num;
    }

    private int fib(int n) {
        if (1 == n || 2 == n) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public void run() {
        System.out.println("Fib-" + this.num + ": " + fib(this.num));
    }
}
