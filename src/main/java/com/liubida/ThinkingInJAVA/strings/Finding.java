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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.liubida.ThinkingInJAVA.util.Print.*;
public class Finding {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\w+").matcher("Evening is full of the linnet’s wings");
        int i = 0;
        while(m.find(i++)){
            print(m.group());
        }
    }
}
