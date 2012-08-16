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

import static com.liubida.ThinkingInJAVA.util.Print.print;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author liubida
 */
public class ZipCompress {
    static final String file = "./src/io/ZipCompress.zip";
static final String prefix = "./src/io/";
    public static void main(String[] args) throws IOException {
        FileOutputStream f = new FileOutputStream(file);
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);

        for (String s : args) {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(prefix+s));
            zos.putNextEntry(new ZipEntry(prefix+s));
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.flush();
        }
        out.close();


        CheckedInputStream cinSum = new CheckedInputStream(new FileInputStream(file), new Adler32());
        ZipInputStream zis = new ZipInputStream(cinSum);
        BufferedReader in = new BufferedReader(new InputStreamReader(zis));

        ZipEntry z;
        while ((z = zis.getNextEntry()) != null) {
            print("Reading file: "+z.getName());
            String s;
            while((s=in.readLine())!=null){
                System.out.println(s);
            }
        }
        // Checksum valid only after the file has been closed!
        print("Checksum write: " + csum.getChecksum().getValue());
        print("Checksum read: " +  cinSum.getChecksum().getValue());
    }
}
