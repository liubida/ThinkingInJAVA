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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

/**
 * @author liubida
 */
public class LargeMappedFiles {
    static int    length = 0x1FFFF;   //128KB
    static String file   = "./src/io/LargeMappedFiles.out";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        MappedByteBuffer out = new RandomAccessFile(file, "rw").getChannel().map(
                MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            out.put((byte)'x');
        }
        for (int i = length/2; i < length/2+6; i++) {
            System.out.print((char)out.get(i));
        }
    }
}
