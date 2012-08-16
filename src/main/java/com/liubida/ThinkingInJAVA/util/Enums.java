/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-17
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
package com.liubida.ThinkingInJAVA.util;

import java.util.Random;

/**
 * @author liubida
 */
public class Enums {
    private static Random seed = new Random(610);

    public static <T extends Enum<T>> T random(Class<T> e) {
        return random(e.getEnumConstants());
    }

    public static <T extends Enum<T>> T random(T[] values) {
        return values[seed.nextInt(values.length)];
    }
}
