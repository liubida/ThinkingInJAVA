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

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author liubida
 */
public class IntBufferDemo {
    private static final int SIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(SIZE);
        IntBuffer iBuf = buf.asIntBuffer();
        iBuf.put(new int[] { 2, 3, 4, 8, 9 });
        iBuf.flip();
        while(iBuf.hasRemaining()){
            System.out.print(iBuf.get());
            System.out.print(", ");
        }
//        iBuf.rewind();
        iBuf.position(2);
        iBuf.put(new int[] { 5, 6, 7, 12 }, 0, 2);
        iBuf.flip();
        System.out.println();
        for (int i = 0; i < iBuf.limit(); i++) {
            System.out.print(iBuf.get());
            System.out.print(", ");
        }
        System.out.println();
    }
}
