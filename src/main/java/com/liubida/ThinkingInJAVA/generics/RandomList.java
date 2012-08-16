/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-27
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
package com.liubida.ThinkingInJAVA.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author liubida
 *
 */
public class RandomList<T> {
    private List<T> storage = new ArrayList<T>();
    private Random rand = new Random(47);
    public void add(T i){
        storage.add(i);
    }
    public T select(){
        return storage.get(rand.nextInt(storage.size()));
    }
    public static void main(String[] args) {
        RandomList<String> r = new RandomList<String>();
        for (String s : "i am liu bi da".split(" ")) {
            r.add(s);
        }
        int i = 10;
        while(i-->=0){
            System.out.println(r.select());
        }
    }
}
