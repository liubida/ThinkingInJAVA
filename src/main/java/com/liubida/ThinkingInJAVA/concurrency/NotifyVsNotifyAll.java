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
import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-11-3 下午7:39:51
 */
class Blocker {

    synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.println(Thread.currentThread() + " ");
            }
        } catch (Exception e) {
        }
    }

    synchronized void prod() {
        notify();
    }

    synchronized void prodAll() {
        notifyAll();
    }
}

class Task implements Runnable {

    static Blocker blocker = new Blocker();

    public void run() {
        blocker.waitingCall();
    }
}

class Task2 implements Runnable {

    static Blocker blocker = new Blocker();

    public void run() {
        blocker.waitingCall();
    }
}

public class NotifyVsNotifyAll {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Task());
        }
        exec.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            boolean flag = true;

            @Override
            public void run() {
                if (flag) {
                    System.out.println("notify()");
                    Task.blocker.prod();
                    flag = false;
                } else {
                    System.out.println("notifyAll()");
                    Task.blocker.prodAll();
                    flag = true;
                }
            }
        }, 400, 400);
        TimeUnit.MILLISECONDS.sleep(5000);
        timer.cancel();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("task2 notifyAll()");
        Task2.blocker.notifyAll();
        TimeUnit.MILLISECONDS.sleep(500);
        exec.shutdown();
    }
}
