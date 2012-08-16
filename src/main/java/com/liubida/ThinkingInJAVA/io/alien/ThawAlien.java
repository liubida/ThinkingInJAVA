/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-16
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
package com.liubida.ThinkingInJAVA.io.alien;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author liubida
 */
public class ThawAlien {
    public static void main(String[] args) throws FileNotFoundException, IOException,
            ClassNotFoundException {
        final String file = "x.file";
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Object m = in.readObject();
        System.out.println(m.getClass());
    }
}
