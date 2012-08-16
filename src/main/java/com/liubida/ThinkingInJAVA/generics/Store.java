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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Random;

import com.liubida.ThinkingInJAVA.util.Generator;


/**
 * @author liubida
 */
class Product {
    private final int id;
    private String    description;
    private double    price;

    public Product(int id, String desc, double price) {
        this.id = id;
        this.description = desc;
        this.price = price;
    }

    public String toString() {
        return id + ": " + description + ", price: ￥" + price;
    }
    public void changePrice(double change){
        price += change;
    }
    public static Generator<Product> generator = new Generator<Product>(){
        private Random seed = new Random(610);
        @Override
        public Product next() {
            return new Product(seed.nextInt(9999),"Product"+seed.nextInt(100),seed.nextDouble());
        }
    };
}
class Shelf extends ArrayList<Product>{
    public Shelf(int n){
        Generators.fill(this, Product.generator, n);
    }
}
class Aisle extends ArrayList<Shelf>{
    public Aisle(int nShelf, int nProduct){
        for (int i = 0; i < nShelf; i++) {
            this.add(new Shelf(nProduct));
        }
    }
}
class CheckoutStand {}
class Office {}
public class Store extends ArrayList<Aisle>{
    private List<CheckoutStand> checkouts = new ArrayList<CheckoutStand>();
    private Office office = new Office();
    public Store(int nAisle, int nShelf, int nProduct){
        for (int i = 0; i < nAisle; i++) {
            this.add(new Aisle(nShelf, nProduct));
        }
    }
    public String toString(){
        StringBuilder ret = new StringBuilder();
        for (Aisle a : this) {
            for (Shelf s : a) {
                for (Product p : s) {
                    ret.append(p);
                    ret.append("\n");
                }
            }
        }
        return ret.toString();
    }
    public static void main(String[] args) {
        System.out.println(new Store(1,2,3));
        System.out.println("-------------------");
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        Class c3 = ArrayList.class;
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c1==c2);
        System.out.println(c1==c3);
        System.out.println("-------------------");
        List<Integer> l = new ArrayList<Integer>();
        Integer[] i = new Integer[2];
        System.out.println(l.getClass());
        System.out.println(Arrays.toString(l.getClass().getTypeParameters()));
        System.out.println(i.getClass());
        System.out.println(Arrays.toString(i.getClass().getTypeParameters()));
        
    }
}
