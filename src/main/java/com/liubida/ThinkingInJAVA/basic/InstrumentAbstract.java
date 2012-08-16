package com.liubida.ThinkingInJAVA.basic;
/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-26
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

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;

abstract class Instrument {
    /**
     * 如果存在method是abstract的，则class一定是abstract
     */
    abstract void play();

    abstract void adjust();

    void what() {

    }
}

abstract class Human {
    /**
     * 即使没有method是abstract的，class也可以是abstract
     */
    void eat() {

    }

    void drink() {

    }
}

abstract class Hit extends Instrument {
    /**
     * 如果存在abstract的method没有被overridden成normal，则derived的class依然是abstract
     */
    void play() {
        print("Hit");
    }
}

class piano extends Instrument {
    /**
     * 所有method被overridden成normal，则derived的class是normal
     */
    void play() {
        print("pull");
    }
    void adjust() {
        print("twist");
    }
}

class Man extends Human {

}

public class InstrumentAbstract {

}
