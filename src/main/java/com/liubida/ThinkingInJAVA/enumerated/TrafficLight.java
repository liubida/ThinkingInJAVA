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
 */
enum Signal {
    GREEN,
    YELLOW,
    RED
}

public class TrafficLight {
    Signal color = Signal.RED;
    
    public void change() {
        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }
    
    public static void main(String[] args) {

        Signal color = null;
        boolean a = color instanceof Signal;
        System.out.println(a);
    }
}
