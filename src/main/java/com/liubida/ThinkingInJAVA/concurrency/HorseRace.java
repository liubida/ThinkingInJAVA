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
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-11-27 下午1:37:11
 */
class Horse implements Runnable {

    private final int           id;
    private int                 strides = 0;
    private final Random        seed    = new Random(System.currentTimeMillis());
    private final CyclicBarrier barrier;
    private final int           priority;

    public Horse(int id, int priority, CyclicBarrier barrier){
        this.id = id;
        this.barrier = barrier;
        this.priority = priority;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    // strides++;
//                    strides += seed.nextInt(id + 2);
                    strides += seed.nextInt(4);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            s.append(".");
        }
        s.append(id);
        return s.toString();
    }
}

public class HorseRace {

    private final int             FINISH_LINE = 150;
    private final List<Horse>     horses      = new ArrayList<Horse>();
    private final ExecutorService exec        = Executors.newCachedThreadPool();
    private final CyclicBarrier   barrier;

    public HorseRace(int nHorses, final int pause){
        barrier = new CyclicBarrier(nHorses, new Runnable() {

            @Override
            public void run() {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++) {
                    s.append("=");
                }
                System.out.println(s);
                for (Horse horse : horses) {
                    System.out.println(horse.tracks());
                }
                for (Horse horse : horses) {
                    if (horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse + "won!");
                        exec.shutdownNow();
                        return;
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (Exception e) {
                }
            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse horse = null;
            switch (i) {
                case 0: {
                    horse = new Horse(i, Thread.MAX_PRIORITY, barrier);
                    break;
                }
                case 1: {
                    horse = new Horse(i, Thread.MIN_PRIORITY, barrier);
                    break;
                }
                default: {
                    horse = new Horse(i, Thread.NORM_PRIORITY, barrier);
                    break;
                }
            }
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorse = 5;
        int pause = 100;
        new HorseRace(nHorse, pause);
    }
}
