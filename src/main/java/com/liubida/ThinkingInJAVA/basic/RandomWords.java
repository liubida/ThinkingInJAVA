/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-27
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
package com.liubida.ThinkingInJAVA.basic;

/**
 * @author liubida
 *
 */
import java.nio.*;
import java.util.*;

import static com.liubida.ThinkingInJAVA.util.Number.*;
import static com.liubida.ThinkingInJAVA.util.Print.*;

public class RandomWords implements Readable {
    private static Random       rand     = new Random(47);
    private static final char[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] lowers   = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    private int                 count;

    public RandomWords(int n) {
        this.count = n;
    }

    public int read(CharBuffer cb) {
        if (count-- <= 0) {
            return -1;
        }
        for (int i = 0; i < count / 2; i++) {
            cb.append(capitals[rand.nextInt(capitals.length)]);
            cb.append(lowers[rand.nextInt(lowers.length)]);
        }
        if (!IsEven(count)) {
            cb.append('@');
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(new RandomWords(4));
        while (s.hasNext()){
            print(s.next());
        }
            
    }
}
