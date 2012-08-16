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

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.enumerated.Input.ABORT_TRANSACTION;
import static com.liubida.ThinkingInJAVA.enumerated.Input.CHIPS;
import static com.liubida.ThinkingInJAVA.enumerated.Input.DIME;
import static com.liubida.ThinkingInJAVA.enumerated.Input.DOLLAR;
import static com.liubida.ThinkingInJAVA.enumerated.Input.NICKEL;
import static com.liubida.ThinkingInJAVA.enumerated.Input.QUARTER;
import static com.liubida.ThinkingInJAVA.enumerated.Input.SOAP;
import static com.liubida.ThinkingInJAVA.enumerated.Input.SODA;
import static com.liubida.ThinkingInJAVA.enumerated.Input.STOP;
import static com.liubida.ThinkingInJAVA.enumerated.Input.TOOTHPASTE;

import java.util.EnumMap;

enum Category {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);

    private Input[] values;

    private Category(Input... types) {
        values = types;
    }

    private static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);
    static {
        for (Category c : values()) {
            for (Input i : c.values) {
                categories.put(i, c);
            }
        }
    }
    public static Category categorize(Input input){
        return categories.get(input);
    }
}

public class VendingMachine {
//    private static State state = ;
    private static int amount= 0;
    private static Input selection = null;
    enum StateDuration{TRANSIENT}
    enum State{
        RESTING{
            void next(Input input){
                switch(Category.categorize(input)){
                    case MONEY:
                }
            }
        }
    }
}
