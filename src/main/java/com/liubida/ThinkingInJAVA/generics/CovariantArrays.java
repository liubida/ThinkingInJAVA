/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-31
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

/**
 * @author liubida
 */
class Fruit {
}

class Apple extends Fruit {
}

class Orange extends Fruit {
}

class Jonathan extends Apple {
}

public class CovariantArrays {
    public static void main(String[] args) {
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple();
        fruit[1] = new Jonathan();
        System.out.println(fruit.getClass());
        System.out.println(fruit[0].getClass());
        System.out.println(fruit[1].getClass());
        try {
            fruit[0] = new Fruit();   
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            fruit[0] = new Orange();
        } catch (Exception e) {
            System.out.println(e);
        }

        List<Fruit> f = new ArrayList<Fruit>();
        List<Apple> g = new ArrayList<Apple>();
        System.out.println(f.getClass());
        System.out.println(g.getClass());
    }
}
