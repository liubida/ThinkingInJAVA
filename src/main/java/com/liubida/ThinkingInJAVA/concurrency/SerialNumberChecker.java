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

/**
 * @author liubida 2011-10-29 下午4:43:31
 */
class SerialNumberGenerator {

    private static volatile int serialNumer = 0;

    public static synchronized int nextSerialNumber() {
        return serialNumer++;
    }
}

class CircularSet {

    private int[] array;
    private int   len;
    private int   index = 0;

    public CircularSet(int size){
        array = new int[size];
        len = size;
        for (int i = 0; i < len; i++) {
            array[i] = -1;
        }
    }

    public void add(int i) {
        array[index] = i;
        index = (index++) % len;
    }

    public boolean contains(int v) {
        for (int i = 0; i < len; i++) {
            if (array[i] == v) {
                return true;
            }
        }
        return false;
    }
}

public class SerialNumberChecker {

    private static final int       SIZE    = 10;
    private static CircularSet     serials = new CircularSet(1000);
    private static ExecutorService exec    = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {

        @Override
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    System.out.println("Duplicate: " + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                System.err.println("Aborting..");
                exec.shutdown();
                System.exit(0);
            }
        }, 2000);
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new SerialChecker());
        }
    }

}
