/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liubida 2012-3-14 下午1:32:29
 */
class Food {

    private static long count = 0;
    final long          id    = count++;
}

@SuppressWarnings("serial")
class Table extends LinkedList<Food> {

    private int MAX = 10;

    synchronized void put(Food f) {
        try {
            while (this.size() >= MAX) {
                wait();
            }
            this.add(f);
            notifyAll();
        } catch (Exception e) {
        }
    }

    synchronized Food get() {
        try {
            while (this.size() <= 0) {
                wait();
            }
            Food f = this.pop();
            notifyAll();
            return f;
        } catch (Exception e) {
            return null;
        }
    }
}

class Customer implements Runnable {

    Table t;

    Customer(Table t){
        this.t = t;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Food f = t.get();
            System.out.println("eat: " + f);
        }
    }
}

class Chef implements Runnable {

    Table t;

    Chef(Table t){
        this.t = t;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Food f = new Food();
            t.put(f);
            System.out.println("put: " + f);
        }
    }
}

public class Restaurant3 {

    public static void main(String[] args) {
        final ExecutorService exec = Executors.newCachedThreadPool();
        Table t = new Table();
        exec.execute(new Chef(t));
        exec.execute(new Customer(t));

        // TimeUnit.SECONDS.sleep(5);
        new Timer().schedule(new TimerTask() {

            public void run() {
                exec.shutdownNow();
            }
        }, 5000);
    }
}
