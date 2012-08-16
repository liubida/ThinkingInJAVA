/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-3
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
package com.liubida.ThinkingInJAVA.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author liubida
 */
public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while (null != (s = in.readLine())) {
            sb.append(s);
            sb.append("\n");
        }
        in.close();
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        System.out.println(read("./src/io/DirList3.java"));
    }
}
