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
package com.liubida.ThinkingInJAVA.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * @author liubida
 */
class Data implements Serializable {
    private int n;

    public Data(int n) {
        this.n = n;
    }

    public String toString() {
        return Integer.toString(n);
    }
}

public class Worm implements Serializable {
    private Random seed = new Random(610);
    private Data[] d    = { new Data(seed.nextInt(10)), new Data(seed.nextInt(10)),
            new Data(seed.nextInt(10)) };
    private Worm   next = null;
    private char   c;

    public Worm(int count, char c) {
        System.out.println("Worm Constructor: " + c);
        this.c = c;
        if (count-- > 0) {
            next = new Worm(count, (char) (c + 1));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(":");
        sb.append(c);
        sb.append("(");
        for (Data data : d) {
            sb.append(data);
        }
        sb.append(")");
        if (next != null) {
            sb.append(next);
        }
        return sb.toString();
    }

    static final String file = "./src/io/Worm.out";

    public static void main(String[] args) throws FileNotFoundException, IOException,
            ClassNotFoundException {
        Worm w = new Worm(7, 'a');
        System.out.println(w);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject("Worm storage\n");
        out.writeObject(w);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        String s = in.readObject().toString();
        Worm w2 = (Worm) in.readObject();
        System.out.println(w2);
    }
}
