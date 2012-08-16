/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-11-26 下午2:16:59
 */
class Receive implements Runnable {

    private final MyBlockingQueue<String> queue;

    public Receive(MyBlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                String tmp = queue.take();
                System.out.println("receive: " + tmp);
                TimeUnit.MILLISECONDS.sleep(800);
            }
        } catch (InterruptedException e) {
            System.out.println("receive interrupted");
        }

    }

}

class Send implements Runnable {

    private Random                        seed   = new Random(System.currentTimeMillis());
    private final MyBlockingQueue<String> queue;
    private boolean                       isOver = false;

    public Send(MyBlockingQueue<String> queue){
        this.queue = queue;
    }

    public void setIsOver() {
        isOver = true;
    }

    private String getString() {
        int len = seed.nextInt(10);
        char[] ret = new char[len];

        for (int i = 0; i < len; i++) {
            ret[i] = (char) ('a' + seed.nextInt(26));
        }
        return String.valueOf(ret);
    }

    @Override
    public void run() {
        try {
            while (!isOver && !Thread.interrupted()) {
                String tmp = getString();
                queue.put(tmp);
                System.out.println("send: " + tmp);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("send interrupted");
        }
    }

}

public class BlockingQueueForIO {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        MyBlockingQueue<String> queue = new MyBlockingQueue<String>();
        Send s = new Send(queue);
        Receive r = new Receive(queue);
        exec.submit(s);
        exec.submit(r);

        TimeUnit.SECONDS.sleep(1);
        s.setIsOver();
        exec.shutdownNow();
    }
}
