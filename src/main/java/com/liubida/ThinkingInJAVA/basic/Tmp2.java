/*
 * Copyright 2012 sohu.com All right reserved. This software is the
 * confidential and proprietary information of sohu.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with sohu.com.
 */
package com.liubida.ThinkingInJAVA.basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liubida Oct 10, 2012 5:15:44 PM
 */
public class Tmp2 {

    public static List<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        // sohukan005 207e736a4d285c9f8480ae06382210c5402026b4

        String[] a = new String[] { "1", "2" };
        System.out.println(Arrays.deepToString(a));
        // Table t = list.get(1);
        // t.a = "woca";
        // System.out.println(list.get(1).a);
        Integer count = 10;
        for (int i = 0; i < 10; i++) {
            count = count++;
        }
        System.out.println(count++);
        // System.out.println(count++);
        // System.out.println(count++);
        int abc = 1024;
        System.out.println(~abc);
        System.out.println(~abc);
        System.out.println(abc ^ 0xffffffff);

    }
}

class Table {

    String a;
    int b;

    public Table(String a, int b){
        this.a = a;
        this.b = b;
    }
}
