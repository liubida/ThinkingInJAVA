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

import static com.liubida.ThinkingInJAVA.util.Print.print;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @author liubida
 */
public class Blip3 implements Externalizable {
    private int    i;
    private String s;

    public Blip3() {
        print("Blip3 constructor");
    }

    public Blip3(int i, String s) {
        print("Blip3 constructor: int, String");
        this.i = i;
        this.s = s;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(i);
        out.writeObject(s);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        i = in.readInt();
        s = in.readObject().toString();
    }

    public String toString() {
        return s + i;
    }

    public static final String file = "./src/io/Blip3.out";

    public static void main(String[] args) throws FileNotFoundException, IOException,
            ClassNotFoundException {
        Blip3 b3 = new Blip3(610, "liubida");
        print(b3);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(b3);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Blip3 m = (Blip3) in.readObject();
        print(m);
    }

}
