/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-29
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
package com.liubida.ThinkingInJAVA.holding;

/**
 * @author liubida
 *
 */
import java.util.*;

import static com.liubida.ThinkingInJAVA.util.Print.*;

public class Statistics {
    public static void main(String[] args) {
        Random seed = new Random(47);
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        Integer value = 0;
        for (int i = 0; i < 600000; i++) {
            int r = seed.nextInt(10);
            value = m.get(r);
            m.put(r, value == null ? 1 : value + 1);
        }
        print(m);

    }
}
