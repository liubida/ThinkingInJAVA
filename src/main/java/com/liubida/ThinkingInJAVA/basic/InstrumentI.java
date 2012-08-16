package com.liubida.ThinkingInJAVA.basic;

/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-26
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

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;

interface InstrumentI {
    String owner = "liubida";

    void play();

    String what();

    void adjust();  
}

class Stringed implements InstrumentI {
    public void play() {
        print("stringed pull");
    }

    public String what() {
        return "nothing";
    }

    public void adjust() {
        print("stringed adjust");
    }
    public void a(){
        print("liubida");
    }
}
class Wind implements InstrumentI {
    public void play() {
        print("wind dial");
    }

    public String what() {
        return "nothing";
    }

    public void adjust() {
        print("wind adjust");
    }
    
    void printOwner(){
        print("wind "+owner);
    }
    public void a(){
        print("zww");
    }
}
class Brass extends Wind{
    public void play(){
        print("brass dial");
    }
    void printOwner(){
        print("brass "+owner);
    }
}

