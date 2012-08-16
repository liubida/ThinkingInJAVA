/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
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

/**
 * @author liubida 2012-2-5 下午7:11:37
 */
public class HWTest {

    Integer i = 0;

    class Inc implements Runnable {

        public void run() {
            while (!Thread.interrupted()) {
                inc();
            }
            System.out.println("inc " + Thread.currentThread().getId() + " is out!");
        }
    }

    class Dec implements Runnable {

        public void run() {
            while (!Thread.interrupted()) {
                dec();
            }
            System.out.println("dec " + Thread.currentThread().getId() + " is out!");
        }
    }

    private synchronized void inc() {
        System.out.println("i: " + (++i));
    }

    private synchronized void dec() {
        System.out.println("i: " + (--i));
    }

    public static void main(String[] args) {
        final ExecutorService exec = Executors.newCachedThreadPool();

        final HWTest h = new HWTest();
        Inc inc = h.new Inc();
        Inc inc1 = h.new Inc();
        Dec dec = h.new Dec();
        Dec dec1 = h.new Dec();
        exec.execute(inc);
        exec.execute(dec);
        exec.execute(dec1);
        exec.execute(inc1);
        new Timer().schedule(new TimerTask() {

            public void run() {
//                exec.shutdown();
                exec.shutdownNow();
                System.out.println("end, i: " + h.i);
                // System.exit(0);
            }
        }, 3000);
    }
}
