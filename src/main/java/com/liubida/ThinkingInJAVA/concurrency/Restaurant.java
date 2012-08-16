/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liubida 2011-11-3 下午7:59:18
 */
// class Meal {
//
// private final int orderNum;
//
// public Meal(int orderNum){
// this.orderNum = orderNum;
// }
//
// public String toString() {
// return "Meal " + orderNum;
// }
// }
//
// public class Restaurant {
// Meal meal = null;
// WaitPerson waitPerson = new WaitPerson(this);
// Chef chef = new Chef(this);
// ExecutorService exec = Executors.newCachedThreadPool();
// boolean contin = true;
//
// public Restaurant(){
// exec.execute(waitPerson);
// exec.execute(chef);
// }
//
// class WaitPerson implements Runnable {
//
// private Restaurant r;
//
// public WaitPerson(Restaurant r){
// this.r = r;
// }
//
// @Override
// public void run() {
// while (r.contin) {
// synchronized (this) {
// while (null == r.meal) {
// try {
// wait();
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// }
// System.out.println("waitPerson got the meal: " + r.meal);
// synchronized (r.chef) {
// r.meal = null;
// r.chef.notify();
// }
// }
// }
// }
//
// class Chef implements Runnable {
//
// private Restaurant r;
// private int count = 1;
//
// public Chef(Restaurant r){
// this.r = r;
// }
//
// @Override
// public void run() {
// while (r.contin) {
// synchronized (this) {
// while (null != r.meal) {
// try {
// wait();
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// }
// if (10 == count) {
// r.contin = false;
// }
// System.out.print("Order up! ");
// synchronized (r.waitPerson) {
// r.meal = new Meal(count++);
// r.waitPerson.notify();
// }
// }
// }
// }
// public static void main(String[] args) {
// new Restaurant();
// }
// }

class Meal {

    private int orderNum;

    Meal(int orderNum){
        this.orderNum = orderNum;
    }

    public String toString() {
        return "meal: " + orderNum;
    }
}

public class Restaurant {

    final ExecutorService exec       = Executors.newCachedThreadPool();
    Meal                  meal       = null;
    WaitPerson            waitPerson = new WaitPerson(this);
    Chef                  chef       = new Chef(this);
    boolean               flag       = true;

    public Restaurant(){
        exec.execute(waitPerson);
        exec.execute(chef);
    }

    class WaitPerson implements Runnable {

        private Restaurant r;

        public WaitPerson(Restaurant r){
            this.r = r;
        }

        @Override
        public void run() {
            while (r.flag) {
                synchronized (this) {
                    try {
                        while (null == r.meal) {
                            wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (r.chef) {
                    r.meal = null;
                    System.out.println("WaitPerson: " + r.meal);
                    r.chef.notifyAll();
                }
            }
        }
    }

    class Chef implements Runnable {

        private Restaurant r;
        private int        count = 1;

        public Chef(Restaurant r){
            this.r = r;
        }

        @Override
        public void run() {
            while (r.flag) {
                synchronized (this) {
                    try {
                        while (null != meal) {
                            wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (10 == count) {
                    r.flag = false;
                }
                synchronized (r.waitPerson) {
                    r.meal = new Meal(count++);
                    System.out.println("Chef: " + r.meal);
                    r.waitPerson.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
