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
import static com.liubida.ThinkingInJAVA.util.Print.printnb;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * @author liubida
 * 
 */
public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String, Charset> m = Charset.availableCharsets();
        for (String s : m.keySet()) {
            Charset c = m.get(s);
            printnb(c.name());
            Iterator<String> i = c.aliases().iterator();
            if (i.hasNext()) {
                printnb(": ");
                while (i.hasNext()) {
                    printnb(i.next());
                    if (i.hasNext()) {
                        printnb(", ");
                    }
                }
            }
            print();
        }
    }
}
