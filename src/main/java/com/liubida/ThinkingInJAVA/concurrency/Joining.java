/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;


/**
 * @author liubida 2011-10-23 下午7:18:38
 */
class Sleeper extends Thread {

    private String name;
    private int    duration;

    Sleeper(String name, int duration){
        this.name = name;
        this.duration = duration;
        start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(name + " was interrupted. ");
            return;
        }
        System.out.println(name + " was awakened. ");
    }
}

class Joiner extends Thread {

    private String  name;
    private Sleeper sleeper;

    Joiner(String name, Sleeper sleeper){
        this.name = name;
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println(name + " was interrupted. ");
            return;
        }
        System.out.println(name + " join completed. ");
    }
}

public class Joining {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Sleeper liubida = new Sleeper("liubida", 1500);
        Sleeper zww = new Sleeper("zww", 1500);

        Joiner j1 = new Joiner("l", liubida);
        Joiner j2 = new Joiner("z", zww);
        zww.interrupt();
    }
}
