/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import static com.liubida.ThinkingInJAVA.util.Print.print;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-11-8 下午7:15:46
 */
enum Status {
    DRY, BUTTERED, JAMMED
};

class Toast {

    private Status    status = Status.DRY;
    private final int id;

    public Toast(int id){
        this.id = id;
    }

    public void butter() {
        status = Status.BUTTERED;
    }

    public void jam() {
        status = Status.JAMMED;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class Toaster implements Runnable {

    private BlockingQueue<Toast> dryQ;
    private int                  count = 0;
    private Random               rand  = new Random(610);

    public Toaster(BlockingQueue<Toast> dry){
        this.dryQ = dry;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                Toast t = new Toast(count++);
                dryQ.put(t);
                print("dry: " + t);
            }
        } catch (InterruptedException e) {
            print("Toaster interrupted");
        }
        print("Toaster off");
    }
}

class Butterer implements Runnable {

    private BlockingQueue<Toast> dryQ, butteredQ;

    public Butterer(BlockingQueue<Toast> dry, BlockingQueue<Toast> buttered){
        dryQ = dry;
        butteredQ = buttered;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = dryQ.take();
                t.butter();
                butteredQ.put(t);
                print("butter: " + t);
            }
        } catch (InterruptedException e) {
            print("Butterer interrupted");
        }
        print("Butterer off");
    }
}

class Jammer implements Runnable {

    private BlockingQueue<Toast> butteredQ, jammedQ;

    public Jammer(BlockingQueue<Toast> buttered, BlockingQueue<Toast> jammed){
        butteredQ = buttered;
        jammedQ = jammed;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = butteredQ.take();
                t.jam();
                jammedQ.put(t);
                print("jamm: " + t);
            }
        } catch (InterruptedException e) {
            print("Jammer interrupted");
        }
        print("Jammer off");
    }

}

class Eater implements Runnable {

    private BlockingQueue<Toast> jammedQ;

    public Eater(BlockingQueue<Toast> jammed){
        jammedQ = jammed;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = jammedQ.take();
                if (t.getStatus() != Status.JAMMED) {
                    print(">>> Error: " + t);
                    System.exit(1);
                } else {
                    print("eat " + t);
                }
            }
        } catch (InterruptedException e) {
            print("Eater interrupted");
        }
        print("Eater off");
    }
}

public class ToastOMatic {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Toast> dryQueue = new LinkedBlockingQueue<Toast>();
        BlockingQueue<Toast> butteredQueue = new LinkedBlockingQueue<Toast>();
        BlockingQueue<Toast> jammedQueue = new LinkedBlockingQueue<Toast>();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue, butteredQueue));
        exec.execute(new Jammer(butteredQueue, jammedQueue));
        TimeUnit.MILLISECONDS.sleep(1);
        exec.shutdown();

    }
}
