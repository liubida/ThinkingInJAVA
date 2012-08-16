/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liubida 2011-10-29 下午7:57:10
 */
public class AtomicIntegerTest implements Runnable {

    private AtomicInteger i = new AtomicInteger(0);

    public int getValue() {
        return i.get();
    }

    private void inc() {
        i.addAndGet(2);
    }

    public void run() {
        while (true) {
            inc();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                System.err.println("Aborting...");
                System.exit(0);
            }
        }, 8000);
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest t = new AtomicIntegerTest();
        exec.execute(t);
        while (true) {
            if (t.getValue() % 2 != 0) {
                System.err.println("Not even...");
                System.exit(0);
            }
        }
    }
}
