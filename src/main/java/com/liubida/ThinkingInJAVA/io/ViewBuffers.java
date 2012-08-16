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

import static com.liubida.ThinkingInJAVA.util.Print.print;
import static com.liubida.ThinkingInJAVA.util.Print.printnb;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/**
 * @author liubida
 */
public class ViewBuffers {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[] { 'a', 'b', 'c', 'd', 0, 0, 'a' });
        bb.rewind();
        while (bb.hasRemaining()) {
            printnb(bb.position() + ":" + bb.get() + ", ");
        }
        print();
        CharBuffer cb = ((ByteBuffer) bb.rewind()).asCharBuffer();
        printnb("Char Buffer ");
        while (cb.hasRemaining())
            printnb(cb.position() + " -> " + cb.get() + ", ");
        print();
        print();
        IntBuffer ib = ((ByteBuffer) bb.rewind()).asIntBuffer();
        printnb("Int Buffer ");
        while (ib.hasRemaining())
            printnb(ib.position() + " -> " + ib.get() + ", ");
        print();
        LongBuffer lb = ((ByteBuffer) bb.rewind()).asLongBuffer();
        printnb("Long Buffer ");
        while (lb.hasRemaining())
            printnb(lb.position() + " -> " + lb.get() + ", ");
        print();
        ShortBuffer sb = ((ByteBuffer) bb.rewind()).asShortBuffer();
        printnb("Short Buffer|"+sb.order()+"    ");
        while (sb.hasRemaining())
            printnb(sb.position() + " -> " + sb.get() + ", ");
        print();
        ShortBuffer sb1 = ((ByteBuffer) bb.order(ByteOrder.LITTLE_ENDIAN).rewind()).asShortBuffer();
        printnb("Short Buffer|"+sb1.order()+" ");
        while (sb1.hasRemaining())
            printnb(sb1.position() + " -> " + sb1.get() + ", ");
        print();
        DoubleBuffer db = ((ByteBuffer) bb.rewind()).asDoubleBuffer();
        printnb("Double Buffer ");
        while (db.hasRemaining())
            printnb(db.position() + " -> " + db.get() + ", ");

    }
}
