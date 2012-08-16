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

import com.liubida.ThinkingInJAVA.generics.coffee.Coffee;

/**
 * @author liubida
 */
abstract class GenericCreator<T> {
    T element;

    abstract T create();

    public GenericCreator() {
        this.element = create();
    }

    public void f() {
        System.out.println("element: " + element.getClass().getSimpleName());
    }
}

class Creator extends GenericCreator<Coffee> {
    @Override
    Coffee create() {
        return new Coffee();
    }
}

public class CreatorGeneric {
    public static void main(String[] args) {
        Creator c = new Creator();
        c.f();
    }
}
