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
 * @author liubida 2012-2-20 下午4:20:27
 */
class Flag {

    Integer i = 0;

    synchronized void print(int j, char c) {
        try {
            while (j != i % 3) {
                wait();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.print(c);
        i++;
        notifyAll();
    }
}

class AP extends Thread {

    Flag f;

    AP(Flag f){
        this.f = f;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            f.print(0, 'A');
        }
    }
}

class BP extends Thread {

    Flag f;

    BP(Flag f){
        this.f = f;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            f.print(1, 'B');
        }
    }
}

class CP extends Thread {

    Flag f;

    CP(Flag f){
        this.f = f;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            f.print(2, 'C');
        }
    }
}

public class ABCThread {

    public static void main(String[] args) {
        final ExecutorService exec = Executors.newCachedThreadPool();
        Flag f = new Flag();
        exec.execute(new AP(f));
        exec.execute(new BP(f));
        exec.execute(new CP(f));

        new Timer().schedule(new TimerTask() {

            public void run() {
                exec.shutdownNow();
                System.out.println("over");
            }
        }, 100);
    }

}
