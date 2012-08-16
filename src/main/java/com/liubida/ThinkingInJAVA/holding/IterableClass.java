/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-1
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

/**
 * @author liubida
 *
 */
import java.util.*;

import static com.liubida.ThinkingInJAVA.util.Print.*;
public class IterableClass implements Iterable<String>{
    private String[] words = "i love you zheng ww !".split(" ");
    public Iterator<String> iterator(){
        return new Iterator<String>(){
            private int index = 0;
            public boolean hasNext(){
                return index<words.length;
            }
            public String next(){
                return words[index++];
            }
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }
    
    public static void main(String[] args) {
        IterableClass a = new IterableClass();
        for (String s : a) {
            print(s);
        }
        
    }
}
