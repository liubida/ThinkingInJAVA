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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author liubida
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) throws IOException {
        String file = "./src/io/Data.txt";
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(
                file)));
        out.writeDouble(1.2);
        out.writeUTF("luibida");
        out.close();
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        in.close();
    }
}
