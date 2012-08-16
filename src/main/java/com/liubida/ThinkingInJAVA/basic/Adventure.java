/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-27
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
package com.liubida.ThinkingInJAVA.basic;

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;

interface LittleCanFight{
    void fight();
}

interface CanFight extends LittleCanFight{
}

interface CanSwim {
    void swim();
}

interface CanFly {
    void fly();
}

class Person {
    public void fight() {
        print("person fight");
    }
}
abstract class Animal{
    abstract void fight();
}
class Pig extends Animal implements CanFight, CanSwim{
    public void fight(){
    }
    public void swim(){}
}

class Hero extends Person implements CanFight, CanSwim, CanFly {
//    public void fight() {
//        print("hero fight");
//    }

    public void swim() {
        print("hero swim");
    }

    public void fly() {
        print("hero fly");
    }
}

public class Adventure {
    public static void t(CanFight x) {
        x.fight();
    }

    public static void s(CanSwim x) {
        x.swim();
    }

    public static void f(CanFly x) {
        x.fly();
    }

    public static void p(Person x) {
        x.fight();
    }

    public static void h(Hero x) {
        x.fight();
        x.swim();
        x.fly();
    }
    public static void main(String[] args) {
        Person a = new Person();
        Hero b = new Hero();
        Person c = new Hero();
        a.fight();
        b.fight();
        c.fight();
        print("---------------");
        //t(a);s(a);f(a);p(a);h(a);
        t(b);s(b);f(b);p(b);h(b);
        t((Hero)c);s((Hero)c);f((Hero)c);p(c);h((Hero)c);
        

    }
}
