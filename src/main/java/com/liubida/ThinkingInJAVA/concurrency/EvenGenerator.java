/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

/**
 * @author liubida 2011-10-29 上午11:12:36
 */
public class EvenGenerator extends IntGenerator {

    private int currentEven = 0;

    public synchronized int next() {
        if(currentEven <=10000){
            ++currentEven;
            Thread.yield();
            ++currentEven;        
            return currentEven;
        } else{
            cancel();
            return -1;
        }
        
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
