/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.generics;

/**
 * @author liubida 2012-2-27 上午8:39:33
 */

class Employee {

    public String toString() {
        return "employee ~~";
    }
}
public class InstantiateGenericType<T> {

    T x;

    InstantiateGenericType(Class<T> kind){
        try {
            this.x = kind.newInstance();
        } catch (Exception e) {
            System.err.println("exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        InstantiateGenericType<Employee> i = new InstantiateGenericType<Employee>(Employee.class);
        System.out.println(i.x);
    }
}
