/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-5
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
package com.liubida.ThinkingInJAVA.exceptions;

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;

public class Cleanup {
    public static void main(String[] args) {
        try {
            InputFile in = new InputFile("/home/liubida/workspace/" +
            		"ThinkingInJAVA/src/basic/exceptions/InputFile.java");
            String s = null;
            try {
                while(null!= (s=in.getLine())){
                    print(s);
                }                
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                in.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("InputFile construction failed"+e.getMessage());
        }
    }
}
