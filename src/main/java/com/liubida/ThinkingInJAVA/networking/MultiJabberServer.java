/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-19
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
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liubida
 */

class WorkerServer extends Thread {
    private Socket         socket;
    private BufferedReader in;
    private PrintWriter    out;

    public WorkerServer(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
                true);
        start();
    }

    public void run() {
        try {
            while (true) {
                String s = in.readLine();
                if (s.equals("END"))
                    break;
                System.out.println("Echoing: " + s);
                out.println(s);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            System.out.println("Closing...");
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Socket close error...");
            }
        }
    }
}

public class MultiJabberServer {
    static final int PORT = 61062;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server Started...");
        try {
            while (true) {
                Socket socket = server.accept();
                
                try {
                    new WorkerServer(socket);
                } catch (Exception e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
