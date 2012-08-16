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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liubida
 */
public class FileOutputShortcut {
    static String inFile  = "./src/io/FileOutputShortcut.java";
    static String outFile = "./src/io/FileOutputShortcut.out";

    private static void del(){
        File one = new File(outFile);
        if(one.exists()){
            one.delete();
        }
    }
    public static void FileInOut1() throws IOException {
        del();
        FileReader in = new FileReader(inFile);
        FileWriter out = new FileWriter(outFile);
        int c;
        while (-1 != (c = in.read())) {
            out.write(c);
        }
        out.close();
        in.close();
        System.out.println(BufferedInputFile.read(outFile));
    }

    public static void FileInOut2() throws IOException {
        del();
        BufferedReader in = new BufferedReader(new FileReader(inFile));
        PrintWriter out = new PrintWriter(outFile);
        String s;
        int lineCount = 1;
        while (null != (s = in.readLine())) {
            out.printf("%d: %s\n", lineCount++, s);
        }
        out.close();
        in.close();
        System.out.println(BufferedInputFile.read(outFile));
    }
    public static void FileInOut3() throws IOException {
        del();
        BufferedReader in = new BufferedReader(new FileReader(inFile));
        BufferedWriter out = new BufferedWriter(new FileWriter(outFile));
        String s;
        while (null != (s = in.readLine())) {
            out.write(s);
            out.newLine();
        }
        out.close();
        in.close();
        System.out.println(BufferedInputFile.read(outFile));
    }
    public static void main(String[] args) throws IOException {
        FileInOut2();
//        FileInOut1();
    }
}
