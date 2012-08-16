/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-30
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

import com.liubida.ThinkingInJAVA.generics.coffee.Coffee;
import com.liubida.ThinkingInJAVA.generics.coffee.CoffeeGenerator;
import com.liubida.ThinkingInJAVA.util.Generator;

import static com.liubida.ThinkingInJAVA.util.Print.*;

/**
 * @author liubida
 */
public class Generators {
    public static <T> List<T> fill(List<T> l, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++) {
            l.add(gen.next());
        }
        return l;
    }

    public static void main(String[] args) {
        List<Coffee> coffee = fill(new ArrayList<Coffee>(), new CoffeeGenerator(), 10);
        List<Integer> fibonacci = fill(new ArrayList<Integer>(), new Fibonacci(), 10);
        print(coffee);
        print(fibonacci);
    }
}
