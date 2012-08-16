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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liubida
 */
public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService exec1 = Executors.newCachedThreadPool();
        ExecutorService exec2 = Executors.newFixedThreadPool(3);
        ExecutorService exec3 = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            exec1.execute(new LiftOff());
        }
        for (int i = 0; i < 5; i++) {
            exec2.execute(new LiftOff());
        }
        for (int i = 0; i < 5; i++) {
            exec3.execute(new LiftOff());
        }
        exec1.shutdown();
        exec2.shutdown();
        exec3.shutdown();
    }
}
