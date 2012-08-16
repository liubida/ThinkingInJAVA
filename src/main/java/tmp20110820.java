/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-8-20
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


import java.io.IOException;

/**
 * @author liubida
 */

public class tmp20110820 {

    public static void main(String[] args) {
        try {
            System.out.print(exceptionHand());
        } catch (Exception e) {
            System.out.println("xiaochao");
        }
    }

    public static Integer exceptionHand() throws Exception {
        try {
//            int a = 2 / 0;
            throw new IOException("");
            //            FileWriter w = new FileWriter("liubida");
//            return 0;
        } catch (IOException e) {
            System.out.println("zwwIO");
            throw e;
        } catch (Exception e) {
            System.out.println("zww");
            throw e;
        } finally {
            System.out.println("liubida");
            return 1;
        }

    }
}

interface field {
    final int  a = 0;
    static int b = 0;
    public int c = 0;
}
