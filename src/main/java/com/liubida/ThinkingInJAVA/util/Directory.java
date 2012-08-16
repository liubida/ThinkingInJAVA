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
package com.liubida.ThinkingInJAVA.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author liubida
 */
public class Directory {
    public static File[] local(File dir, final String reg) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern p = Pattern.compile(reg);

            @Override
            public boolean accept(File dir, String name) {
                return p.matcher(name).matches();
            }
        });
    }

    public static File[] local(String path, String reg) {
        return local(new File(path), reg);
    }

    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs  = new ArrayList<File>();

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo o) {
            files.addAll(o.files);
            dirs.addAll(o.dirs);
        }

        public String toString() {
            //            StringBuilder sb = new StringBuilder();
            //            sb.append("Dir:\n");
            //            for (File f : dirs) {
            //                sb.append(f.getName() + " ");
            //            }
            //            sb.append("\nFiles:\n");
            //            for (File f : files) {
            //                sb.append(f.getName() + " ");
            //            }
            //            return sb.toString();
            return "dirs: " + PPrint.pformat(dirs) + "\n\nfiles: " + PPrint.pformat(files);
        }
    }

    public static TreeInfo walk(File start, String reg) {
        return recurseDirs(start, reg);
    }

    public static TreeInfo walk(String start, String reg) {
        return walk(new File(start), reg);
    }

    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start), ".*");
    }

    static TreeInfo recurseDirs(File start, String reg) {
        TreeInfo ret = new TreeInfo();
        for (File f : start.listFiles()) {
            if (f.isDirectory()) {
                ret.dirs.add(f);
                ret.addAll(recurseDirs(f, reg));
            } else {
                if (Pattern.matches(reg, f.getName())) {
                    ret.files.add(f);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(walk("./src"));
        } else {
            for (String arg : args)
                System.out.println(walk(arg));
        }
    }
}
