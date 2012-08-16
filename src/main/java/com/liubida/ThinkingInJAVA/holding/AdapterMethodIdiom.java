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
import static com.liubida.ThinkingInJAVA.util.Print.*;

import java.util.*;

@SuppressWarnings({ "serial", "unchecked" })
class MultiIterableArrayList<T> extends ArrayList<T> {
    public MultiIterableArrayList() {
        super();
    }

    public MultiIterableArrayList(Collection<T> c) {
        super(c);
    }

    /**
     * ReversibleArrayList类中实现了一个reversed的method reversed()返回Iterable接口 return
     * new Iterable<T>(){...} -> 声明了一个匿名类A，并返回A的实例
     * A继承(implements)自Iterable接口，必须实现iterator方法 return new Iterator() {...} ->
     * 声明了一个匿名类B，并返回B的实例 B继承(implements)自Iterator接口，必须实现3个方法
     */
    public Iterable<T> reversed() {
        //返回接口
        return new Iterable<T>() {
            //匿名类A需要实现的方法
            public Iterator iterator() {
                //返回接口
                return new Iterator() {
                    //匿名类B需要实现的方法
                    int index = size() - 1;

                    //                    public void printCurrent(){
                    //                        print(get(index));
                    //                    }

                    @Override
                    public boolean hasNext() {
                        return index > -1;
                    }

                    @Override
                    public T next() {
                        return get(index--);
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            };
        };
    }

    public Iterable<T> randomized() {
        return new Iterable() {
            public Iterator iterator() {
                T[] tmp = (T[]) toArray();
                ArrayList<T> shuffled = new ArrayList<T>(Arrays.asList(tmp));
                //                谁能告诉我clone的方法为什么不行
                //                try {
                //                    shuffled = (ArrayList<T>)super.clone();
                //                } catch (CloneNotSupportedException e) {
                //                    e.printStackTrace();
                //                }
                Collections.shuffle(shuffled, new Random(610));
                return shuffled.iterator();

            };
        };
    }
}

public class AdapterMethodIdiom {
    public static void main(String[] args) {
        MultiIterableArrayList<String> r = new MultiIterableArrayList<String>(
                Arrays.asList("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15".split(" ")));
        for (String s : r) {
            printnb(s + " ");
        }
        print();
        for (String s : r.reversed()) {
            printnb(s + " ");
        }
        print();
        for (String s : r.randomized()) {
            printnb(s + " ");
        }
    }
}
