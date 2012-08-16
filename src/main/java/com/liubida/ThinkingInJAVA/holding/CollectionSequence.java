/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-29
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

public class CollectionSequence extends AbstractCollection<Integer>{
    private Integer[] num = {0,1,2,3,4,5,6,7,8,9};
    public Iterator<Integer> iterator(){
        return new Iterator<Integer>(){
            private int index = 0;
            public boolean hasNext(){
                return index < num.length; 
            }
            public void remove(){
                throw new UnsupportedOperationException();
            }
            public Integer next(){
                return num[index++];
            }
        };
    }
    public int size(){
        return num.length;
    }
}
