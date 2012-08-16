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
package com.liubida.ThinkingInJAVA.generics.coffee;

import java.util.Iterator;
import java.util.Random;

import com.liubida.ThinkingInJAVA.util.Generator;


/**
 * @author liubida
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private Class[]       types = { Americano.class, Breve.class, Cappuccino.class, Latte.class,
            Mocha.class        };
    private static Random rand  = new Random(89);
    private int           size;

    public CoffeeGenerator() {
    }

    public CoffeeGenerator(int size) {
        this.size = size;
    }

    class CoffeeIterator implements Iterator<Coffee> {
        private int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CoffeeGenerator gen = new CoffeeGenerator();
        for (int i = 0; i < 7; i++) {
            System.out.println(gen.next());
        }
        System.out.println("--------------------");
        for (Coffee coffee : new CoffeeGenerator(10)) {
            System.out.println(coffee);
        }
    }
}
