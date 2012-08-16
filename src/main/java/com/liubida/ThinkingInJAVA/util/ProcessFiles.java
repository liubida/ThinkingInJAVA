/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-3
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
package com.liubida.ThinkingInJAVA.util;

import java.io.File;
import java.io.IOException;

/**
 * @author liubida
 */
public class ProcessFiles {
    public interface Strategy {
        void process(File file);
    }

    private Strategy strategy;
    private String   ext;

    public ProcessFiles(Strategy strategy, String ext) {
        this.strategy = strategy;
        this.ext = ext;
    }

    public void processDirectoryTree(File root) throws IOException {
        for (File file : Directory.walk(root.getAbsolutePath(), ".*\\." + ext)) {
            strategy.process(file.getCanonicalFile());
        }
    }

    public void start(String[] args) {
        try {
            if (0 == args.length) {
                processDirectoryTree(new File("."));
            } else {
                for (String s : args) {
                    File tmp = new File(s);
                    if (tmp.isDirectory()) {
                        processDirectoryTree(tmp);
                    } else {
                        if (!s.endsWith("." + ext)) {
                            s += "." + ext;
                        }
                        strategy.process(new File(s).getCanonicalFile());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        new ProcessFiles(new ProcessFiles.Strategy() {
            @Override
            public void process(File file) {
                System.out.println(file);
            }
        }, "java").start(args);
    }
}
