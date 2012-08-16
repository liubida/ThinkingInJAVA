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

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;

/**
 * @author liubida
 */
public class LockingMappedFiles {
    static final int    SIZE = 0x1000000;
    static FileChannel  fc;
    static final String file = "./src/io/LockingMappedFile.dat";

    public static void main(String[] args) throws IOException {
        fc = new RandomAccessFile(file, "rw").getChannel();
        MappedByteBuffer out = fc.map(MapMode.READ_WRITE, 0, SIZE);
        for (int i = 0; i < SIZE; i++) {
            out.put((byte) 'x');
        }
        new LockAndModify(out, 0, 0 + SIZE / 3);
        new LockAndModify(out, SIZE / 2, SIZE / 2 + SIZE / 4);
    }

    private static class LockAndModify extends Thread {
        private ByteBuffer buff;
        private int        start, end;

        public LockAndModify(ByteBuffer m, int start, int end) {
            this.start = start;
            this.end = end;
            m.limit(end);
            m.position(start);
            this.buff = m.slice();
            start();
        }

        public void run() {
            try {
                FileLock fl = fc.lock(start, end, false);
                System.out.println("Locked: " + start + " to " + end);
                while (buff.position() < buff.limit() - 1) {
                    byte t = buff.get();
                    buff.position(buff.position()-1);
                    buff.put((byte) (t + 1));
                }
                fl.release();
                System.out.println("UnLocked: " + start + " to " + end);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
