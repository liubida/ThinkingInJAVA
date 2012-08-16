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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author liubida
 */
public class GetChannel {
    private static final int    SIZE = 1024;
    private static final String file = "./src/io/GetChannelData.out";

    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream(file).getChannel();
        //        fc.write(ByteBuffer.wrap("wo shi liubida".getBytes()));
        ByteBuffer a = ByteBuffer.wrap("wo shi liubida".getBytes());
        //        ByteBuffer a =ByteBuffer.allocate(SIZE);
        //        a.put("wo shi liu111bida".getBytes());
        //        a.flip();
        fc.write(a);
        fc.close();

        fc = new RandomAccessFile(file, "rw").getChannel();
        fc.position(fc.size());
        fc.write(ByteBuffer.wrap("wo shi zww".getBytes()));
        fc.close();

        fc = new FileInputStream(file).getChannel();
//        ByteBuffer buf = ByteBuffer.allocate(SIZE);     // lower  coupling with the OS
        ByteBuffer buf = ByteBuffer.allocateDirect(SIZE); // higher coupling with the OS
        fc.read(buf);
        buf.flip();

        while (buf.hasRemaining()) {
            System.out.print((char) buf.get());
        }
        buf.rewind();
        
        String encoding = System.getProperty("file.encoding");
        System.out.println("\nDecoded using " + encoding + ": "
        + Charset.forName(encoding).decode(buf));
    }
}
