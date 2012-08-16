package com.liubida.ThinkingInJAVA.typeinfo.pets;
/**
 * @author liubida
 *
 */

/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-18
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author liubida
 */
class Individual {
    private static long counter;
    private final long  id   = counter++;
    private String      name = null;

    public Individual() {
    }

    public Individual(String name) {
        this.name = name;
    }

    public long id() {
        return id;
    }

    public String toString() {
        return name == null ? this.getClass().getName() : name;
    }
}

class Person extends Individual {
    public Person(String name) {
        super(name);
    }
}

class Pet extends Individual {
    public Pet() {
    }

    public Pet(String name) {
        super(name);
    }
}

class Dog extends Pet {
}

class Cat extends Pet {
}

class Mutt extends Dog {
}

class pug extends Dog {
}

class EgyptianMau extends Cat {
}

class Manx extends Cat {
}

class Cymric extends Manx {
}

abstract class PetCreator {
    private Random seed = new Random(69);
    protected Random getSeed(){
        return seed;
    }
    public abstract List<Class<? extends Pet>> types();
    public Pet randomPet(){
        int n = seed.nextInt(types().size());
        try {
            return types().get(n).newInstance();
        } catch (Exception e) {
            return null;
        }
    }
    private Pet[] createArray(int size){
        Pet[] result = new Pet[size];
        for (int i = 0; i < size; i++) {
            result[i] = randomPet();
        }
        return result;
    }
    public List<Pet> createList(int size){
        List<Pet> result = new ArrayList<Pet>();
        Collections.addAll(result, createArray(size));
        return result;
    }
    public static void main(String[] args) {
        
    }
}

class ForNameCreator extends PetCreator{
    private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();
    private static String[] typeName = {
        "typeinfo.pets.Mutt",
        "typeinfo.pets.Pug",
        "typeinfo.pets.EgyptianMau",
        "typeinfo.pets.Manx",
        "typeinfo.pets.Cymric"
    };
    @SuppressWarnings("unchecked")
    private static void loader(){
        for (String s : typeName) {
            try {
                types.add((Class<? extends Pet>)Class.forName(s));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    static {loader();}
    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
}

public class PetCount {
static class PetCounter extends HashMap<String, Integer>{
    private static final long serialVersionUID = 1L;

    public void count(String type){
        Integer quantity = get(type);
        if(null == quantity){
            put(type, 1);
        }else{
            put(type, quantity+1);
        }
    }
}

}
