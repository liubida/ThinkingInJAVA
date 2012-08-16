/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-20
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
package com.liubida.ThinkingInJAVA.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author liubida
 */
class WorkerClient extends Thread {
    private Socket         socket;
    private BufferedReader in;
    private PrintWriter    out;
    private int            id          = threadCount++;
    private static int     threadCount = 0;

    public WorkerClient(InetAddress addr) {
        System.out.println("Creating Client: " + id);
        try {
            socket = new Socket(addr, MultiJabberServer.PORT);
        } catch (IOException e) {
            System.out.println("socket error");
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream())), true);
            start();
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                System.out.println("socket close error");
            }
        }
    }

    public void run() {
        try {
            for (int i = 0; i < 25; i++) {
                out.println("Client " + id + ": " + (char) ('a' + i));
                String s = in.readLine();
                System.out.println(s);
            }
            out.println("END");
        } catch (IOException e) {
            System.err.println("IO Exception");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("socket close error");
            }
            threadCount--;
        }
    }

    public static int threadCount() {
        return threadCount;
    }
}

public class MultiJabberClient {
    static final int    MAX_THREADS = 3;
    static final String HOST        = "127.0.0.1";

    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress addr = InetAddress.getByName(HOST);
        while (WorkerClient.threadCount() <= MAX_THREADS) {
            new WorkerClient(addr);
            Thread.currentThread().sleep(10);
        }
    }
}
