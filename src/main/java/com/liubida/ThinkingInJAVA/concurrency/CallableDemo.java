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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author liubida
 */
class FibonacciTaskWithResult implements Callable<String> {

    private Integer num;

    public FibonacciTaskWithResult(Integer num){
        this.num = num;
    }

    private Integer fib(Integer n) {
        if (1 == n || 2 == n) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    @Override
    public String call() {
        Date now = new Date();
        long start = System.currentTimeMillis();
        Integer result = fib(this.num);
        long end = System.currentTimeMillis();
        String ret = "start:" + now.toString() + "| time costs: " + (end - start) + "| result:" + result;
        return ret;
    }
}

class TaskModel {

    Integer        id;
    Future<String> fu;
}

public class CallableDemo {

    public static void main(String[] args) {
        List<TaskModel> resultList = new ArrayList<TaskModel>();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            TaskModel tm = new TaskModel();
            tm.id = i;
            tm.fu = exec.submit(new FibonacciTaskWithResult(i));
            resultList.add(tm);
        }
        System.out.println("liubida");
        for (TaskModel tm : resultList) {
            final TaskModel t = tm;
            try {
                new Thread() {

                    public void run() {
                        try {
                            System.out.println(t.id + ": " + t.fu.get(5000, TimeUnit.MILLISECONDS));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            } catch (Exception e) {
            } finally {
                exec.shutdown();
            }
        }
    }
}
