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
import java.io.*;

public class InputFile {
    private BufferedReader in;

    public InputFile(String fname) throws Exception {
        try {
            in = new BufferedReader(new FileReader(fname));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            try {
                in.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            throw e;
        }
    }

    public String getLine() {
        String s;
        try {
            s = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("getLine() failed" + e.getMessage());
        }
        return s;
    }

    public void dispose() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("dispose failed" + e.getMessage());
        }
    }
}
