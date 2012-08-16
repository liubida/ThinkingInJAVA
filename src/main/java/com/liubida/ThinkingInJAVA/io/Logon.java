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
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.liubida.ThinkingInJAVA.io.alien.Blip3;

import static com.liubida.ThinkingInJAVA.util.Print.*;

/**
 * @author liubida
 */
public class Logon implements Serializable {
    private Date             date = new Date();
    private String           username;
    private transient String password;
    static final String      file = "./src/io/Logon.out";

    public Logon(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String toString() {
        return "logon info: \n username: " + username + "\n date: " + date + "\n password: "
                + password;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        //        stream.defaultWriteObject();
        stream.writeObject(password);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        //        stream.defaultReadObject();
        password = (String) stream.readObject();
    }

    public static void main(String[] args) throws Exception {
        Logon me = new Logon("liubida", "iloveu");
        print(me);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(me);
        out.close();

        TimeUnit.MILLISECONDS.sleep(1000);

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Logon me2 = (Logon) in.readObject();
        print(me2);
    }
}
