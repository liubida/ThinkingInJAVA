/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-8
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

/**
 * @author liubida
 *
 */
import java.io.*;

import static com.liubida.ThinkingInJAVA.util.Print.*;

public class Hex {
    public static String format(byte[] data){
        StringBuilder result = new StringBuilder();
        long n = 0;
        for (byte b : data) {
            if(0==(n++)%16){
                result.append(String.format("\n%05x: ", b));
            }
            result.append(String.format("%02x ", b));n++;
        }
        
        result.append('\n');
        return result.toString();
    }
    public static void main(String[] args) {
        File f = new File("/home/liubida/workspace/");
        if(0==args.length){
        }
    }
}
