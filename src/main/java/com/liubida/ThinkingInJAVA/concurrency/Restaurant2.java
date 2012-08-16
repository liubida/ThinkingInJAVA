///*
// * Copyright 2011 Alibaba.com All right reserved. This software is the
// * confidential and proprietary information of Alibaba.com ("Confidential
// * Information"). You shall not disclose such Confidential Information and shall
// * use it only in accordance with the terms of the license agreement you entered
// * into with Alibaba.com.
// */
//package com.liubida.ThinkingInJAVA.concurrency;
//
//import java.util.LinkedList;
//import java.util.Random;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author liubida 2011-11-18 下午1:36:38
// */
//class Food {
//
//    static long count = 0;
//    final long  id    = count++;
//};
//
//@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
//class Table extends LinkedList {
//
//    int maxSize;
//
//    Table(int maxSize){
//        this.maxSize = maxSize;
//    }
//
//    synchronized void put(Food f) {
//        while (this.size() >= maxSize) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//            }
//        }
//        this.add(f);
//        notifyAll();
//    }
//    
//    synchronized Food get() {
//        while (this.size() <= 0) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//            }
//        }
//        Food f = (Food) this.remove();
//        notifyAll();
//        return f;
//    }
//    }
//
//class Customer implements Runnable {
//
//    Table  t;
//    Random r = new Random(621);
//
//    Customer(Table table){
//        this.t = table;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            Food f = t.get();
//            eat(f);
//        }
//    }
//
//    private void eat(Food f) {
//        try {
//            TimeUnit.MILLISECONDS.sleep(400 + r.nextInt(200));
//        } catch (InterruptedException e) {
//        }
//        System.out.println("eat food:" + f.id);
//    }
//}
//
//class Chef implements Runnable {
//
//    Table  t;
//    Random r = new Random(610);
//
//    Chef(Table table){
//        this.t = table;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (!Thread.currentThread().isInterrupted()) {
//                Food f = make();
//                t.put(f);
//            }
//        } catch (Exception e) {
//            return;
//        } finally {
//            System.out.println("i'm out");
//        }
//
////        while (!Thread.currentThread().isInterrupted()) {
////            try {
////                Food f = make();
////                t.put(f);
////            } catch (Exception e) {
////            } finally {
////                System.out.println("i'm out");
////            }
////        }
//
//    }
//
//    private Food make() {
//        try {
//            TimeUnit.MILLISECONDS.sleep(200 + r.nextInt(200));
//        } catch (InterruptedException e) {
//        }
//        Food f = new Food();
//        System.out.println("done food:" + f.id);
//        return f;
//    }
//}
//
//public class Restaurant2 {
//
//    Table           t    = new Table(10);                  // 桌子上可以放10个菜
//    ExecutorService exec = Executors.newCachedThreadPool();
//
//    void openUp() {
//        for (int i = 0; i < 4; i++) {
//            exec.execute(new Chef(t));
//        }
//        for (int i = 0; i < 10; i++) {
//            exec.execute(new Customer(t));
//        }
//    }
//
//    public static void main(String[] args) {
//        new Restaurant2().openUp();
//    }
//}