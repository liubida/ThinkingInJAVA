/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-8-28
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
 */
public class tmp20110828 {
    static int a(int i, int g) {
        return ((i + g - 1) & ~(g - 1));
    }

    public static void main(String[] args) {
        int a = 0x7fffffff;
        int b = 0x80000001;
        System.out.println(a);
        System.out.println(a + 1);
        System.out.println(b);
    }

    int chooseFar(int i) {
        switch (i) {
            case 84:
                return 0;
            case 0:
                return 0;
            case -100:
                return -1;
            case 100:
                return 1;
            default:
                return -1;
        }
    }

    int chooseNear(int i) {
        switch (i) {
            case 0:
                return -1;
            case 1:
                return 0;
            case 5:
                return 1;
            default:
                return -1;
        }
    }

}
