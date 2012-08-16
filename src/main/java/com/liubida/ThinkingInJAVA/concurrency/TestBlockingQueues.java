/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import static com.liubida.ThinkingInJAVA.util.Print.print;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author liubida 2011-11-3 下午9:11:38
 */
class LiftOffRunner implements Runnable {

    private BlockingQueue<LiftOff> queue;

    public LiftOffRunner(BlockingQueue<LiftOff> queue){
        this.queue = queue;
    }

    public void add(LiftOff l) {
        try {
            queue.put(l);
        } catch (InterruptedException e) {
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                LiftOff o = queue.take();
                o.run();
            }
        } catch (InterruptedException e) {
        }
        System.out.println("exiting liftoffrunner");
    }
}

public class TestBlockingQueues {

    static void getkey() {
        try {
            // Compensate for Windows/Linux difference in the
            // length of the result produced by the Enter key:
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void getkey(String message) {
        print(message);
        getkey();
    }

    static void test(String msg, BlockingQueue<LiftOff> queue) {
        print(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);
        t.start();
        for (int i = 0; i < 5; i++)
            runner.add(new LiftOff(5));
//        getkey("Press ‘Enter’ (" + msg + ")");
        t.interrupt();
        print("Finished " + msg + " test");
    }

    public static void main(String[] args) {
//        test("LinkedBlockingQueue", // Unlimited size
//             new LinkedBlockingQueue<LiftOff>());
         test("ArrayBlockingQueue", // Fixed size
         new ArrayBlockingQueue<LiftOff>(3));
        // test("SynchronousQueue", // Size of 1
        // new SynchronousQueue<LiftOff>());
    }
}
