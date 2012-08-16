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

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Scanner;

/**
 * @author liubida
 *
 */
public class BetterRead {
    public static BufferedReader input = new BufferedReader(
            new StringReader("Sir Robin of Camelot\n22 1.61803"));
    public static void main(String[] args) {
        Scanner stdin = new Scanner(input);
//        stdin.useDelimiter("\\w");
        System.out.print(stdin.delimiter());
        String name = stdin.nextLine();
        int age = stdin.nextInt();
        double fav = stdin.nextDouble();
        System.out.println(name);
        System.out.println(age);
        System.out.println(fav);
    }
}
