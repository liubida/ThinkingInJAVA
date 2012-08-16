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

/**
 * @author liubida
 */

public class JabberServer {
    public static final int PORT = 61062;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server starting: ...");
        try {
            Socket socket = server.accept();
            try {
                System.out.println("Connection accepted: " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream())), true);
                while (true) {
                    String s = in.readLine();
                    if (s.equals("END")) {
                        break;
                    }
                    System.out.println("Echoing: " + s);
                    out.println(s);
                }
            } finally {
                System.out.println("Server closing: ...");
                socket.close();
            }
        } finally {
            server.close();
        }

    }

}
