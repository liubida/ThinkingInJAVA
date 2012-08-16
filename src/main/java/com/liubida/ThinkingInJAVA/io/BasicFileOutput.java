/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-8
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
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author liubida
 */
public class BasicFileOutput {
    static String file = "./src/io/BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("./src/io/BasicFileOutput.java"));
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        int lineCount = 1;
        String s;
        while (null != (s = in.readLine())) {
            out.write(lineCount++ +"| ");
            out.write(s);
            out.newLine();
        }
        out.close();
        in.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
