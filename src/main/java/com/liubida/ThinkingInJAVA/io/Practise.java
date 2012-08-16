/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author liubida 2012-2-17 上午10:42:31
 */
public class Practise {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream in1 = new FileInputStream(new File("/home/liubida"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Reader r1 = new StringReader("");
        Reader r2 = new FileReader("");
    }
}
