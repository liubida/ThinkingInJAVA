/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-2
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
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * @author liubida
 */
public class DirList3 {

    public static void main(final String[] args) {
        // File path = new File("/home/liubida/");
        // String[] list;
        // if (0 == args.length) {
        // list = path.list();
        // path.listFiles();
        // } else {
        // list = path.list(new FilenameFilter() {
        //
        // private Pattern p = Pattern.compile(args[0]);
        //
        // @Override
        // public boolean accept(File dir, String name) {
        // return p.matcher(name).matches();
        // }
        // });
        // Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        // }
        // for (String s : list) {
        // System.out.println(s);
        // }
        listFile();
    }

    static void listFile() {
        File path = new File("/home/liubida");
        String[] list = null;
        final String name = "*";
        list = path.list(new FilenameFilter() {

            private Pattern p = Pattern.compile(name);

            public boolean accept(File dir, String name) {
                return p.matcher(name).matches();
            }
        });

        for (String s : list) {
            System.out.println(s);
        }
    }
}
