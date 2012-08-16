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

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * @author liubida
 *
 */
public class FileLocking {
    private static String file           = "./src/io/MappedIO.out";
    public static void main(String[] args) throws IOException, InterruptedException {
        FileOutputStream f = new FileOutputStream(file);
        FileLock fl = f.getChannel().tryLock();
        if(null!=fl){
            TimeUnit.MILLISECONDS.sleep(1000);
            fl.release();
            System.out.println("file unlocked");
        }
        f.close();
    }
}
