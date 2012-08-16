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
 * @author liubida 2012-2-15 下午4:48:23
 */
public class HW {

    Integer i = 0;

    class inc implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    i++;
                }
            }
            System.out.println("inc " + Thread.currentThread().getId() + " is out!");
        }
    }

    class dec implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    i--;
                }
            }
            System.out.println("dec " + Thread.currentThread().getId() + " is out!");
        }
    }

    public static void main(String[] args) {
        final ExecutorService exec = Executors.newCachedThreadPool();
        HW h = new HW();
        exec.execute(h.new inc());
        exec.execute(h.new inc());
        exec.execute(h.new dec());
        exec.execute(h.new dec());

        // TimeUnit.SECONDS.sleep(5);
        new Timer().schedule(new TimerTask() {

            public void run() {
                exec.shutdownNow();
            }
        }, 3000);
    }
}
