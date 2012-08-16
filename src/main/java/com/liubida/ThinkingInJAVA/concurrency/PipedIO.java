/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liubida 2011-11-26 上午10:13:47
 */
class Sender implements Runnable {

    private final Random      seed = new Random(System.currentTimeMillis());
    private final PipedWriter out;

    public Sender(){
        out = new PipedWriter();
    }

    public PipedWriter getPipedWriter() {
        return out;
    }

    private char genChar() {
        return (char) ('a' + seed.nextInt(26));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                char c = genChar();
                System.out.println("Wirte: " + c);
                out.write(c);
                TimeUnit.MILLISECONDS.sleep(seed.nextInt(500));
            }
        } catch (IOException e) {
            System.out.println("sender write exceptino");
        } catch (InterruptedException e) {
            System.out.println("sender sleep interrupted");
        }
    }
}

class Receiver implements Runnable {

    private final PipedReader in;

    public Receiver(Sender sender) throws IOException{
        in = new PipedReader(sender.getPipedWriter());
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Read: " + (char) in.read());
            }
        } catch (IOException e) {
            System.out.println("receiver read exceptino");
        }
    }
}

public class PipedIO {

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Sender send = new Sender();
        exec.submit(send);
        Receiver receive = new Receiver(send);
        exec.submit(receive);
//        TimeUnit.SECONDS.sleep(10);
//        exec.shutdownNow();
    }
}
