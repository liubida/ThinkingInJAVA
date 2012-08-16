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

class Shared {
    public static long counter  = 0;        //the total Shared Object Count         

    private int         refCount = 0;        //the reference count of this object
    private final long  id       = counter++; //the id of this object in the whole Shared Objects

    public Shared() {
        print("Creating " + this);
    }

    public void addRef() {
        refCount++;
    }

    protected void dispose() {
        if (--refCount == 0) {
            print("Dispoing " + this);
        }
    }

    @Override
    
    public String toString() {
        return "Shared " + id;
    }
}

class Composing {
    private Shared      shared;

    public Composing(Shared shared) {
        print("Creating " + this);
        this.shared = shared;
        this.shared.addRef();
    }

    protected void dispose() {
        print("disposing " + this);
        shared.dispose();
    }
}

public class ReferenceCounting {
    public static void main(String[] args) {
        Shared shared = new Shared();
        Composing[] composing = { new Composing(shared), new Composing(shared),
                new Composing(shared), new Composing(shared), new Composing(shared) };
        print(shared.toString());
        for (Composing c : composing)
            c.dispose();
    }
}
