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
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author liubida
 */
public class DirList {
    public static void main(String[] args) {
        File path = new File("./src/advanced/generics");
        String[] list;
        if(0==args.length){
            list = path.list();
            path.listFiles();
        }else{
            list = path.list(new DirFilter(args[0]));
            Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        }
        for (String s : list) {
            System.out.println(s);
        }
    }
}

class DirFilter implements FilenameFilter{
    private Pattern p;
    public DirFilter(String reg){
        p = Pattern.compile(reg);
    }
    @Override
    public boolean accept(File dir, String name) {
        return p.matcher(name).matches();
    }
}
