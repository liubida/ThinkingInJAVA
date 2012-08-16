/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-11-26 下午4:39:07
 */

class Chopstick {

    private boolean taken = false;

    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
        notifyAll();
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}

class Philosopher implements Runnable {

    private static int      count = 0;
    private final int       id    = count++;
    private final Chopstick left;
    private final Chopstick right;
    private final Random    seed;

    public Philosopher(Chopstick left, Chopstick right){
        this.left = left;
        this.right = right;
        this.seed = new Random(System.currentTimeMillis());
    }

    private void thinking() throws InterruptedException {
        // TimeUnit.MILLISECONDS.sleep(seed.nextInt(10));
    }

    private void eating() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(seed.nextInt(800));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("philosopher " + id + "->thinking");
                thinking();
                System.out.println("philosopher " + id + "->left");
                left.take();
                System.out.println("philosopher " + id + "->right");
                right.take();
                System.out.println("philosopher " + id + "->eating");
                eating();
                System.out.println("philosopher " + id + "->drop");
                left.drop();
                right.drop();
            }
        } catch (InterruptedException e) {
            System.out.println("philosopher interrupted");
        }
    }
}

public class DeadLockingDiningPhilosophers {

    public static void main(String[] args) throws IOException {
        final int STICK_SIZE = 5;
        final int PHI_SIZE = 5;
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Chopstick> sticks = new ArrayList<Chopstick>();
        for (int i = 0; i < STICK_SIZE; i++) {
            sticks.add(new Chopstick());
        }
        for (int i = 0; i < PHI_SIZE; i++) {
            if (i < PHI_SIZE - 1) {
                exec.submit(new Philosopher(sticks.get(i % STICK_SIZE), sticks.get((i + 1) % STICK_SIZE)));
            } else {
                exec.submit(new Philosopher(sticks.get((i + 1) % STICK_SIZE), sticks.get(i % STICK_SIZE)));
            }
        }
        System.in.read();
        exec.shutdownNow();
    }
}
