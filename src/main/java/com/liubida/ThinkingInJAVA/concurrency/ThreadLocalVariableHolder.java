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
import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-10-29 下午8:32:14
 */

class Accessor implements Runnable {

    private static int Count = 0;
    private final int  id    = Count++;

    @Override
    public void run() {
        // while ((c = ThreadLocalVariableHolder.get()) < 10000000) {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("[" + id + "]: " + ThreadLocalVariableHolder.get());
            ThreadLocalVariableHolder.inc();
        }
    }
}

public class ThreadLocalVariableHolder {

    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {

                                                  protected Integer initialValue() {
                                                      return 0;
                                                  }
                                              };

    public static void inc() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }
    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Accessor());
        }
        TimeUnit.MILLISECONDS.sleep(2000);
        exec.shutdownNow();
    }
}
