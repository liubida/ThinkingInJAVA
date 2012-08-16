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
 * @author liubida 2011-11-7 下午3:28:35
 */
public class TestDaemon implements Runnable {

    private int count = 0;

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new TestDaemon(), "TestDaemon");
        a.setDaemon(true);
        TimeUnit.MILLISECONDS.sleep(1000);
        a.start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("TestDaemon Running!!!" + count++);
        }
    }

}
