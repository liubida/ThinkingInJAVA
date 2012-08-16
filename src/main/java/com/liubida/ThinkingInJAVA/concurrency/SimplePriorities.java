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

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liubida
 */
public class SimplePriorities implements Callable<String> {

    private int             countDown = 5;
    private volatile double d;
    private int             priority;

    public SimplePriorities(int priority){
        this.priority = priority;
    }

    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    public String call() {
        Thread.currentThread().setPriority(this.priority);
        while (true) {
            for (int i = 0; i < 100000000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (0 == i % 1000) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (0 == countDown--) {
                break;
            }
        }
        return "i'm out from " + String.valueOf(d);
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(new SimplePriorities(Thread.MIN_PRIORITY));
        // exec.submit(new SimplePriorities(Thread.NORM_PRIORITY));
        exec.submit(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
