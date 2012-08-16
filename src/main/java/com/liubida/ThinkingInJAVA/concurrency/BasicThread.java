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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liubida
 */
public class BasicThread {

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(new LiftOff()).start();
            // new Thread(new FibonacciTask(i + 1)).start();
        }

        Map<Integer, String> map = new ConcurrentHashMap<Integer, String>();
        for (int i = 0; i < 8; i++) {
            map.put(i, "liubida" + i);
        }
        
        ExecutorService exec = Executors.newCachedThreadPool();
        Thread t = null;
        
    }
}
