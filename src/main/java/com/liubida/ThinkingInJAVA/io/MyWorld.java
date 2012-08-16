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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.liubida.ThinkingInJAVA.util.Print.*;

/**
 * @author liubida
 */
class House implements Serializable {
}

class Animal implements Serializable {
    private String name;
    private House  preferredHouse;

    Animal(String nm, House h) {
        name = nm;
        preferredHouse = h;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return name + "[" + super.toString() + "], " + preferredHouse + "\n";
    }
}

public class MyWorld {
    public static void main(String[] args) throws Exception {
        House house = new House();
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("pig", house));
        animals.add(new Animal("cat", house));
        animals.add(new Animal("dog", house));

        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals);
        
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);

        ObjectInputStream i1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream i2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));
        List<Animal> l1 = (List<Animal>) i1.readObject();
        List<Animal> l2 = (List<Animal>) i1.readObject();
        List<Animal> l3 = (List<Animal>) i2.readObject();
        print("animals1: " + l1);
        print("animals2: " + l2);
        print("animals3: " + l3);

        l1.get(0).setName("liubida");
        print(l2.get(0).getName());
    }
}
