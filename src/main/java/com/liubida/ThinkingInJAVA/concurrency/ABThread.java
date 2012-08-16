/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2012-2-20 下午4:02:20
 */

class AT implements Runnable {

    BT b;

    AT(BT b){
        this.b = b;
    }

    public void run() {
        try {
            System.out.println("A is sleeping");
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        b.start();
        try {
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("A is over");
    }
}

class BT extends Thread {

    public void run() {
        try {
            System.out.println("B is sleeping");
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

public class ABThread {

    public static void main(String[] args) {
        new Thread(new AT(new BT())).start();
    }
}
