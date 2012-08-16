/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-10-30 下午12:57:12
 */

class Count {

    private int    count;
    private Random rand = new Random();

    public synchronized int inc() {
        // 这里这么写只是为了Thread.yield()
        int tmp = count;
        if (rand.nextBoolean()) {
            Thread.yield();
        }
        count = ++tmp;
        return count;
    }

    public synchronized int value() {
        return count;
    }
}

class Entrance implements Runnable {

    private static Count            count         = new Count();
    private static List<Entrance>   entrances     = new ArrayList<Entrance>();
    private static volatile boolean canceled      = false;
    private int                     number;
    private static int              entranceCount = 0;
    private int                     id            = entranceCount++;
    private int                     priority;

    public static void cancel() {
        canceled = true;
    }

    public Entrance(int priority){
        this.priority = priority;
        entrances.add(this);
    }

    public void run() {
        Thread.currentThread().setPriority(this.priority);
        while (!canceled) {
//            synchronized (this) {
                number++;
//            }
            count.inc();
            System.out.println(this);
        }
        System.out.println("Stopping entrance " + id + ": " + getValue());
    }

//    public synchronized int getValue() {
        public synchronized int getValue() {
        return number;
    }

    public String toString() {
        return "entrance " + id + ": " + getValue();
    }

    public static int sumC() {
        return count.value();
    }

    public static int sum() {
        int sum = 0;
        for (Entrance e : entrances) {
            sum += e.getValue();
        }
        return sum;
    }
}

public class OrnamentalGarden {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Entrance(Thread.NORM_PRIORITY));
        }
        TimeUnit.MILLISECONDS.sleep(30000);
        Entrance.cancel();
        exec.shutdown();
        if (!exec.awaitTermination(500, TimeUnit.MILLISECONDS)) {
            System.out.println("some tasks were not terminated!");
        }
        System.out.println("Total1: " + Entrance.sum());
        System.out.println("Total2: " + Entrance.sumC());
    }
}
