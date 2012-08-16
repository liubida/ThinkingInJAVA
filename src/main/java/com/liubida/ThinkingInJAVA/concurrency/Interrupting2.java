/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liubida 2011-10-30 下午6:45:57
 */
class BlockedMutex {

    private Lock lock = new ReentrantLock();

    public BlockedMutex(){
        lock.lock();
    }

    public void f() {
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
        }
    }
}

class Blocked2 implements Runnable {

    BlockedMutex blocked = new BlockedMutex();

    @Override
    public void run() {
        System.out.println("1");
        blocked.f();
        System.out.println("2");
    }
}

public class Interrupting2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt");
        t.interrupt();
    }
}
