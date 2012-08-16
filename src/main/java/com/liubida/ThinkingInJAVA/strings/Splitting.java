/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-9
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
public class Splitting {
    public static String knights =
        "Then, when you have found the shrubbery, you must " +
        "cut down the mightiest tree in the forest... " +
        "with... a herring!";
    public static void split(String regex){
        String[] tmp = knights.split(regex);
        StringBuilder o = new StringBuilder();
        for (String s : tmp) {
            o.append("#");
            o.append(s);
        }
        System.out.println(o);
    }
    public static void main(String[] args) {
//        split(" ");
        split("\\W+");
        split("n\\w+");
        /*
         * Split the string Splitting.knights on the words "the" or “you."
         */
        split("the|you");
    }
}
