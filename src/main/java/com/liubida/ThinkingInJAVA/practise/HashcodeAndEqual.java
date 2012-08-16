/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.practise;

import java.util.HashSet;

/**
 * @author liubida 2012-2-21 下午1:22:33
 */

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HashcodeAndEqual {

    public static void main(String[] args) {
        HashSet set = new HashSet();
        for (int i = 0; i <= 3; i++) {
            set.add(new Demo(i));
        }
        System.out.println(set);
        System.out.println(set.contains(new Demo(1)));
    }

    private static class Demo {

        private int value;

        public Demo(int value){
            this.value = value;
        }

        public String toString() {
            return " value = " + value;
        }

        public boolean equals(Object o) {
            Demo a = (Demo) o;
            return (a.value == value) ? false : false;
        }

        public int hashCode() {
            return value;
        }
    }
}
