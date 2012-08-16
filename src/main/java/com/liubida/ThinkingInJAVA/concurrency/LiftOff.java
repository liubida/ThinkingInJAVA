/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-10-16
 * $Id$
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @author liubida
 */
public class LiftOff implements Runnable {

    private int        countDown = 6;
    private static int taskCount = 0;
    private final int  id        = taskCount++;

    public LiftOff(){
    }

    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    private String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "over!") + ") ";
    }

    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            // Thread.yield();
            // Thread.sleep(100);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}

class LifeOff implements Runnable {

    public void run() {
    }
}
