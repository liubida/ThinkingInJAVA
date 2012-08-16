/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-10-23 下午2:34:33
 */
public class SimpleDaemons implements Runnable {

    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + ":" + this);
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("started!");
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("ended!");
    }
}
