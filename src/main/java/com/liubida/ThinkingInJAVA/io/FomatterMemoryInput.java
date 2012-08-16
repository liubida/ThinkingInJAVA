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

import java.io.DataInputStream;
import java.io.IOException;
import java.io.StringBufferInputStream;

/**
 * @author liubida
 */
@SuppressWarnings("deprecation")
public class FomatterMemoryInput {
    public static void main(String[] args) {
        try {
            DataInputStream in = new DataInputStream(new StringBufferInputStream(
                    BufferedInputFile.read("./src/io/FomatterMemoryInput.java")));
            int c;
            while(-1!=(c=in.read())){
                System.out.print((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
