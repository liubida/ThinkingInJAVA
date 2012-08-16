/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-17
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
package com.liubida.ThinkingInJAVA.strings;

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.print;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JGrep {
    public static void main(String[] args) {
        if(args.length<2){
            print("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
        Matcher m = p.matcher("");
//        for (String line : new TextFile()) {
//            
//        }
    }
}
