/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-19
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
package com.liubida.ThinkingInJAVA.typeinfo.pets;

/**
 * @author liubida
 *
 */
import com.liubida.ThinkingInJAVA.util.TypeCounter;

import static com.liubida.ThinkingInJAVA.util.Print.*;
public class PetCount3 {
public static void main(String[] args) {
    TypeCounter c = new TypeCounter(Pet.class);
    for (Pet p : new LiteralPetCreator().createList(20)) {
        c.count(p);
    }
    print(c);
}
}
