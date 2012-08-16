/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-10
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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liubida
 */
public class ByteBufferToFile {
    private static final int    SIZE = 1024;
    private static final String file = "./src/io/ByteBufferToFile.out";

    public static void main(String[] args) throws IOException {
        char[] cc = "woshiliubida".toCharArray();
        int[] i = new int[] { 1, 2, 3, 4, 5 };
        ByteBuffer b1 = ByteBuffer.wrap("111111111111111111111111".getBytes());
        CharBuffer cb = b1.asCharBuffer();
        cb.put(cc);
        cb.flip();
        while(cb.hasRemaining()){
            System.out.print(cb.get());
            System.out.print(", ");
        }
//        while(b1.hasRemaining()){
//            System.out.print(b1.get());
//            System.out.print(", ");
//        }
//        ByteBuffer b2 = ByteBuffer.wrap();

        FileChannel fc = new FileOutputStream(file).getChannel();
        fc.write(b1);
        fc.close();

        BufferedReader in = new BufferedReader(new FileReader(file));
        int c;
        while ((c = in.read()) != -1) {
            System.out.println((char)c);
        }
        //    IntBuffer ib =  ByteBuffer.allocate(SIZE).asIntBuffer();

    }
}
