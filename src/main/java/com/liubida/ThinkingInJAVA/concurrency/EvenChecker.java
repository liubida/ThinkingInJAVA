/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liubida 2011-10-29 上午11:03:05
 */
public class EvenChecker implements Runnable {

    private IntGenerator generator;
    private static int   taskCount = 0;
    private final int    id        = taskCount++;

    public EvenChecker(IntGenerator generator){
        this.generator = generator;
    }

    public void run() {
        while (!generator.isCanceled()) {
            int v = generator.next();
            if (v % 2 != 0) {
                System.out.println("thead id:" + id + ", val:" + v + " not even");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator g, int count) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(g));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator g) {
        test(g, 30);
    }
}
