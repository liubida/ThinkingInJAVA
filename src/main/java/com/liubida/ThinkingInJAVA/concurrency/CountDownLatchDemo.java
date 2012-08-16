/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-11-27 上午10:19:32
 */
class TaskPortion implements Runnable {

    private static int           count = 0;
    private final int            id    = count++;
    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch){
        this.latch = latch;
    }

    private void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2000);
    }

    @Override
    public void run() {
        try {
            // System.out.println("Thread: " + id + " dowork...");
            doWork();
            // System.out.println("Thread: " + id + " countDown...");
            latch.countDown();
        } catch (InterruptedException e) {
            System.out.println("TaskPortion: " + id + " is interrupted");
        }
    }
}

class WaitTask implements Runnable {

    private static int           count = 0;
    private final int            id    = count++;
    private final CountDownLatch latch;

    public WaitTask(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("WaitTask: " + id + " is interrupted");
        }
        System.out.println("wait over");
    }
}

class ProcessTask implements Runnable {

    private static int           count = 0;
    private final int            id    = count++;
    private final CountDownLatch latch;
    private final long           size;

    public ProcessTask(CountDownLatch latch, long size){
        this.latch = latch;
        this.size = size;
    }

    @SuppressWarnings("unused")
    private double getPercent(double percent) {
        double a = percent * 100;
        int b = (int) a;
        int c = a - b < 0.5 ? b : b + 1;
        System.out.println((double) c / 100);
        return (double) c / 100;
    }

    private String getProcessBar(long cur) {
        String percentStr = new DecimalFormat("###,###,###.##").format(1 - cur * 1.0 / size);
        StringBuilder sb = new StringBuilder();
        sb.append(percentStr);
        for (long i = 0; i < 100 * (1 - cur * 1.0 / size); i++) {
            sb.append("%");
        }
        return sb.toString();
    }

    @Override
    public void run() {
        try {
            while (true) {
                long cur = latch.getCount();
                if (cur <= 0) break;
                System.out.println(getProcessBar(cur));
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("WaitTask: " + id + " is interrupted");
        }

    }
}

public class CountDownLatchDemo {

    public static void main(String[] args) {
        final Random seed = new Random(System.currentTimeMillis());
        final int SIZE = 1000;
        final ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(SIZE);
        WaitTask t1 = new WaitTask(latch);

        new Thread() {

            public void run() {
                try {
                    for (int i = 0; i < SIZE; i++) {
                        TimeUnit.MILLISECONDS.sleep(seed.nextInt(2000));
                        exec.submit(new TaskPortion(latch));
                    }
                } catch (InterruptedException e) {
                }
            }
        }.start();
        ProcessTask t2 = new ProcessTask(latch, SIZE + 0L);
        exec.execute(t1);
        exec.execute(t2);
    }
}
