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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-11-27 下午2:48:58
 */
class Pool<T> {

    private int                size;
    private List<T>            items = new ArrayList<T>();
    private volatile boolean[] checkedOut;
    private Semaphore          available;

    public Pool(Class<T> classObject, int size){
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        for (int i = 0; i < size; i++) {
            try {
                items.add(classObject.newInstance());
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }
    }

    public T checkOut() throws InterruptedException {
        available.acquire();
        return getItem();
    }

    public void checkIn(T x) {
        if (releaseItem(x)) {
            available.release();
        }
    }

    private synchronized T getItem() {
        for (int i = 0; i < size; i++) {
            if (!checkedOut[i]) {
                return items.get(i);
            }
        }
        return null;
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if (-1 == index) {
            return false;
        }
        if (checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        } else {
            return false;
        }
    }
}

class Fat {

    private volatile double d;
    private static int      counter = 0;
    private final int       id      = counter++;

    public Fat() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(1000);
    }

    public void operation() {
        System.out.println(this);
    }

    public String toString() {
        return "Fat id:" + id;
    }
}

class CheckoutTasks<T> implements Runnable {

    private static int    counter = 0;
    private final int     id      = counter++;
    private final Pool<T> pool;

    public CheckoutTasks(Pool<T> pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println("checked out " + item);
            TimeUnit.MILLISECONDS.sleep(1);
            pool.checkIn(item);
            System.out.println("checked in " + item);
        } catch (InterruptedException e) {
        }
    }
}

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException, IOException {
        final int SIZE = 5;
        ExecutorService exec = Executors.newCachedThreadPool();
        final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new CheckoutTasks<Fat>(pool));
        }
//        List<Fat> list = new ArrayList<Fat>();
//        for (int i = 0; i < SIZE; i++) {
//            Fat f = pool.checkOut();
//            f.operation();
//            list.add(f);
//        }
//        exec.execute(new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    Fat f = pool.checkOut();
//                    System.out.println("second: " + f);
//                } catch (InterruptedException e) {
//                    System.out.println("blocked interrupt");
//                }
//            }
//        });
//        System.in.read();
//        for (Fat fat : list) {
//            pool.checkIn(fat);
//        }
    }
}
