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

import java.util.Formatter;
import java.util.Iterator;

import com.liubida.ThinkingInJAVA.util.Generator;


/**
 * @author liubida
 */
public class Fibonacci implements Generator<Integer> {
    private int count = 0;

    private int fib(int n) {
        int f1 = 1;
        int f2 = 1;
        int ret = 0;
        if (n <= 2) {
            return 1;
        }
        for (int i = 3; i <= n; i++) {
            ret = f1 + f2;
            f1 = f2;
            f2 = ret;
        }
        return ret;
    }

    @Override
    public Integer next() {
        return fib(++count);
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        Formatter f = new Formatter(System.out);
        for (int i = 1; i <= 18; i++) {
            f.format("%5d", fib.next());
            if (0 == i % 7) {
                System.out.println();
            }
        }
        System.out.println();
        for (int i : new IterableFibonacci(18)) {
            f.format("%d ", i);
        }
    }
}

class IterableFibonacci extends Fibonacci implements Iterable<Integer> {
    private int count;

    public IterableFibonacci(int count) {
        this.count = count;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public Integer next() {
                count--;
                return IterableFibonacci.this.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
