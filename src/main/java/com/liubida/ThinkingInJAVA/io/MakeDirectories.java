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
package com.liubida.ThinkingInJAVA.io;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;

/**
 * @author liubida
 */
public class MakeDirectories {
    private static void usage() {
        System.err.println("Usage:MakeDirectories path1 ...\n" + "Creates each path\n"
                + "Usage:MakeDirectories -d path1 ...\n" + "Deletes each path\n"
                + "Usage:MakeDirectories -r path1 path2\n" + "Renames from path1 to path2");
        System.exit(1);
    }

    private static void fillData(File file) {
        Formatter p = new Formatter(System.out);
        long tmp;
        if (file.isDirectory()) {
            tmp = file.getTotalSpace();
        } else {
            tmp = file.length();
        }
        p.format("%1s%1s%1s%1s %8d %15tc %s", file.isDirectory() ? 'd' : '-', file.canRead() ? 'r'
                : '-', file.canWrite() ? 'w' : '-', file.canExecute() ? 'x' : '-', tmp, 
                        new Date(file.lastModified()), file.getName());
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
        }
        System.out.println(Arrays.toString(args));
        fillData(new File(args[0]));
    }
}
