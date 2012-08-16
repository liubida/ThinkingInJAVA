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


import java.util.EnumSet;

import com.liubida.ThinkingInJAVA.generics.coffee.Coffee;
import com.liubida.ThinkingInJAVA.util.BasicGenerator;
import com.liubida.ThinkingInJAVA.util.Generator;


/**
 * @author liubida
 *
 */
public class BasicGeneratorDemo {
    public static void main(String[] args) {
        Generator<Coffee> gen = new BasicGenerator<Coffee>(Coffee.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
        Generator<Coffee> g = BasicGenerator.create(Coffee.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(g.next());
        }
    }
}
