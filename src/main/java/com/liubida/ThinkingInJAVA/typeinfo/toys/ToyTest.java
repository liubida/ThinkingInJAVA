/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-18
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
package com.liubida.ThinkingInJAVA.typeinfo.toys;

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.print;

interface HasBatteries {
}

interface Waterproof {
}

interface Shoots {
}

class Toy {
    Toy() {
    }

    Toy(int i) {
    }
    public String toString(){
        return "Toy";
    }
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
    FancyToy() {
        super(1);
    }
    public String toString(){
        return "FancyToy";
    }
}

public class ToyTest {
    static void printInfo(Class cc) {
        print("---------------------------------");
        print("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
        print("Simple name: " + cc.getSimpleName());
        print("Canonical name : " + cc.getCanonicalName());
        print("---------------------------------");
    }

    public static void main(String[] args) {

        Class c = null;
        try {
            c = Class.forName("basic.typeinfo.toys.FancyToy");
        } catch (ClassNotFoundException e) {
            print("class not found");
            System.exit(1);
        }
        printInfo(c);
        for (Class i : c.getInterfaces()) {
            printInfo(i);
        }
        Class up = c.getSuperclass();
        Object o = null;
        try {
            o = up.newInstance();
        } catch (Exception e) {
            System.exit(1);
        }
        printInfo(o.getClass());
        
        Toy t = new Toy();
        FancyToy ft = new FancyToy();
        print(ft instanceof FancyToy);
        
    }
}
