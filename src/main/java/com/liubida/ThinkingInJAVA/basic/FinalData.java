package com.liubida.ThinkingInJAVA.basic;
/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-25
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
 * 
 * @author liubida
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;

class Value {
    int i;

    public Value(int i) {
        this.i = i;
    }
}
final class ValueF extends Value{
    int j;
    public ValueF(int i, int j){
        super(i);
        this.j = j;
    }
}

public class FinalData {
    public static void main(String[] args) {
        Value v1 = new Value(1);
        final Value v2 = new Value(2);
        //v2 = v1;
        ValueF v3 = new ValueF(3,4);
        v3.i = 0;
        v3.j = 5;
        print(v1.i);
        print(v2.i);
    }
}
