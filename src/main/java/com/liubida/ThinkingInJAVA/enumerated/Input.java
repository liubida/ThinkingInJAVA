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
package com.liubida.ThinkingInJAVA.enumerated;

import java.util.Random;

/**
 * @author liubida
 */
public enum Input {
    NICKEL(5),
    DIME(10),
    QUARTER(25),
    DOLLAR(100),
    TOOTHPASTE(200),
    CHIPS(75),
    SODA(100),
    SOAP(50),
    ABORT_TRANSACTION {
        public int amount() {
            throw new RuntimeException("ABORT");
        }
    },
    STOP {
        public int amount() {
            throw new RuntimeException("STOP");
        }
    };
    int value;

    private Input(int value) {
        this.value = value;
    }

    private Input() {
    }

    int amount() {
        return value;
    }

    private static Random seed = new Random(610);

    public static Input randomSelect() {
        return values()[seed.nextInt(values().length-1)];
    }
}
