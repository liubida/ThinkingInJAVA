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

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegularExpression {
    public static void main(String[] args) {
        if (args.length < 2) {
            print("Usage:\njava TestRegularExpression " + "characterSequence regularExpression+");
            System.exit(0);
        }
        print("Input: \"" + args[0] + "\"");
        for (String a : args) {
            print("Regular expression: " + a);
            Pattern p = Pattern.compile(a);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
            
            Matcher m = p.matcher(args[0]);
            while (m.find()) {
                print("Match :" + m.group() + " at position, " + m.start() + "-" + m.end());
            }
        }
    }
}
