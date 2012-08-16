/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-13
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

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author liubida
 */
public class GZIPcompress {
    static final String inFile  = "./src/io/GZIPcompress.java";
    static final String outFile = "./src/io/GZIPcompress.gz";

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(inFile));
        BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(
                new FileOutputStream(outFile)));
        int c;
        while (-1 != (c = in.read())) {
            out.write(c);
        }
        out.close();
        in.close();

        BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(
                new FileInputStream(outFile))));
        String s;
        while ((s = in2.readLine()) != null) {
            System.out.println(s);
        }
        in2.close();
    }
}
