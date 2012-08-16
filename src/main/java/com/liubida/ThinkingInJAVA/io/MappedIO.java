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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * @author liubida
 */
public class MappedIO {
    private static int    numOfInts      = 4000000;
    private static int    numOfUbuffInts = 200000;
    private static String file           = "./src/io/MappedIO.out";

    private abstract static class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest() throws IOException {
            print(this.name + ": ");
            long start = System.nanoTime();
            test();
            double result = System.nanoTime() - start;
            System.out.format("%.2f\n", result / 1.0e9);
        }

        public abstract void test() throws IOException;
    }

    private static Tester[] tests = { new Tester("Stream Write") {
                                      @Override
                                      public void test() throws IOException {
                                          BufferedOutputStream out = new BufferedOutputStream(
                                                  new FileOutputStream(file));
                                          for (int i = 0; i < numOfInts; i++) {
                                              out.write(i);
                                          }
                                          out.close();
                                      }
                                  }, new Tester("Mapped Write") {
                                      @Override
                                      public void test() throws IOException {
                                          FileChannel fc = new RandomAccessFile(file, "rw")
                                                  .getChannel();
                                          IntBuffer ib = fc.map(MapMode.READ_WRITE, 0,
                                                  fc.size() * 4).asIntBuffer();
                                          for (int i = 0; i < numOfInts - 1; i++) {
                                              ib.put(i);
                                          }
                                          fc.close();
                                      }
                                  },new Tester("Stream Read") {
                                      @Override
                                      public void test() throws IOException {
                                          BufferedInputStream in = new BufferedInputStream(
                                                  new FileInputStream(file));
                                          for (int i = 0; i < numOfInts; i++) {
                                              in.read();
                                          }
                                          in.close();
                                      }
                                  }, new Tester("Mapped Read") {
                                      @Override
                                      public void test() throws IOException {
                                          FileChannel fc = new FileInputStream(file).getChannel();
                                          IntBuffer ib = fc.map(MapMode.READ_ONLY, 0, fc.size())
                                                  .asIntBuffer();
                                          while (ib.hasRemaining()) {
                                              ib.get();
                                          }
                                          fc.close();
                                      }

                                  } };

    public static void main(String[] args) throws IOException {
        for (Tester test : tests)
            test.runTest();
    }
}
