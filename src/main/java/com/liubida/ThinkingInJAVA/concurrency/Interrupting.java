/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-10-30 下午2:23:47
 */

class SleepBlocked implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("sleepBlocked interrupted!");
        }
    }
}

class IOBlocked implements Runnable {

    private InputStream in;

    public IOBlocked(InputStream in){
        this.in = in;
    }

    @Override
    public void run() {
        try {
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("IOBlocked interrupted!");
            } else {
                throw new RuntimeException(e);
            }
        }
    }
}

class SynchronizedBlocked implements Runnable {

    private synchronized void f() {
        while (true) {
            Thread.yield();
        }
    }

    public SynchronizedBlocked(){
        new Thread() {

            public void run() {
                f();
            }
        }.start();
    }

    @Override
    public void run() {
        f();
    }
}

public class Interrupting {

    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> result = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("interrupting " + r.getClass().getName());
        result.cancel(true);
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(8);
        System.exit(0);
    }
}
