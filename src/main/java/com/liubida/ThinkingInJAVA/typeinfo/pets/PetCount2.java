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

import static com.liubida.ThinkingInJAVA.util.Print.print;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author liubida
 */
class LiteralPetCreator extends PetCreator {
    @SuppressWarnings("unchecked")
    static final List<Class<? extends Pet>> allTypes = Collections.unmodifiableList(Arrays.asList(
                                                             Pet.class, Dog.class, Cat.class,
                                                             Mutt.class, EgyptianMau.class,
                                                             Manx.class, Cymric.class));

    @Override
    public List<Class<? extends Pet>> types() {
        return allTypes.subList(0, allTypes.size());
    }
}

public class PetCount2 {
    static class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
        private static final long serialVersionUID = 1L;

        public PetCounter() {
            for (Class<? extends Pet> c : LiteralPetCreator.allTypes) {
                put(c, 0);
            }
        }
        public void count(Pet p) {
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                if (pair.getKey().isInstance(p)) {
                    put(pair.getKey(), pair.getValue() + 1);
                }
            }
        }
        public String toString(){
            StringBuilder result = new StringBuilder("[");
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                result.append(pair.getKey().getSimpleName());
                result.append("=");
                result.append(pair.getValue());
                result.append(", ");
            }
            result.delete(result.length()-2, result.length());
            result.append("]");
            return result.toString();
        }
    }

    public static void main(String[] args) {
        PetCounter pc = new PetCounter();
        for (Pet pet : new LiteralPetCreator().createList(20)) {
            pc.count(pet);
        }
        print(pc);
    }
}
