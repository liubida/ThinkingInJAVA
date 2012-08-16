/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-28
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
package com.liubida.ThinkingInJAVA.holding;

import java.util.*;

import static com.liubida.ThinkingInJAVA.util.Print.*;
/**
 * @author liubida
 *
 */


public class AddingGroups {
    public static void main(String[] args) {
        Collection<Integer> c = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
        List<Integer> l = new ArrayList<Integer>(Arrays.asList(4,5));
        l.add(6);
        c.addAll(l);
        Collections.addAll(c, 7);
        
        for (Integer i : c) {
            print(i);
        }
//        Iterator<Integer> it = l.iterator();
        ListIterator<Integer> it = l.listIterator(0);
        
        
//        print(it.previousIndex());
        
        
        while(it.hasNext()){
            print(it.previousIndex());
            print(it.nextIndex());
            it.next();
            it.set(999);
//            print(it.next() + ", " + it.nextIndex() +
//                    ", " + it.previousIndex() + "; ");    
        }
        print(l);
        
        
    }
}
