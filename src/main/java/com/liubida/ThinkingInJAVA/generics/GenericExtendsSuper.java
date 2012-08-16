/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-1
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
package com.liubida.ThinkingInJAVA.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liubida
 */
class E{}
class D extends E{}
class C extends D{}
class B extends C{}
final class A extends B{}

public class GenericExtendsSuper {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        /**
         * List<? extends E> 表明：list中的元素的类型的上届是类E
         * 编译器只知道list可能存放类E，或者E的子类，编译器无法知道list中元素到底是什么类型
         * 
         */
        List<? extends E> list = new ArrayList<D>(); 
        /**
         * 这里，list.add(anything)都是会编译出错
         * 编译器的逻辑：如果list中的元素是E或者E的子类，那么这个赋值就有可能是非法的
         * list.add(new D());   ---> 编译器想，可能list中元素是A，那么参数传入就存在这样的赋值: A tmp = new D(); 所以报错
         * 
         * ---分割线------------------------------
         * list.add(new A());   ---> 对于这个也报错，我觉得是编译器没想明白。注意这时候类A是final class，即不能再有子类。
         *                           按照上文的逻辑，编译器如果能想到list中元素的类型不可能是A的子类，那么参数传入的赋值是合法的。
         *                           但是依然报错，显然就是编译器就把这一条特殊情况下的思路给忽略了。
         * 
         */
//        list.add(new Object()); //编译错误
//        list.add(new E());      //编译错误
//        list.add(new D());      //编译错误
//        list.add(new C());      //编译错误
//        list.add(new B());      //编译错误
//        list.add(new A());      //编译错误
        list.add(null);         //正确，但是没有任何意义。null没有类型信息，

        /**
         * list中元素的类型的上届是类E，所以用E去接收list.get(0)出来的元素是合法的
         * 编译器想，如果list中元素类型是E，那用D去接收就是非法，所以报错
         * 
         */
        E e = list.get(0);
//        D d = list.get(0);      //编译错误
//        C c = list.get(0);      //编译错误
//        B b = list.get(0);      //编译错误
//        A a = list.get(0);      //编译错误
        
        /**
         * List<? super B> 表明：list中的元素的类型的下届是类B
         * 编译器只知道list可能存放类B，或者B的父类，编译器无法知道list中元素到底是什么类型
         * 
         */        
        List<? super B> alist = new ArrayList<D>();
        /**
         * alist.add(O): 如果O>B，则报错；如果O<=B，则编译通过
         * 编译器的逻辑：如果alist中的元素是B或者B的父类，如果O>B，那么这个赋值就有可能是非法的；如果O<=B，那么这个赋值就是合法的。
         * alist.add(new E());   ---> 编译器想，可能alist中元素是B，那么参数传入就存在这样的赋值: B tmp = new E(); 所以报错
         * alist.add(new A());   ---> 编译器想，alist中元素类型最低也是B，那么参数传入就存在这样的赋值: B tmp = new A(); 所以通过
         * 
         */
//      alist.add(new Object()); //编译错误
//      alist.add(new E());      //编译错误
//      alist.add(new D());      //编译错误
//      alist.add(new C());      //编译错误
        alist.add(new B());      
        alist.add(new A());      
        
        /**
         * alist中元素的类型的下界是类B，list.get(0)返回的类型可能是B，C，D，E... Object
         * D d1 = alist.get(0)       ---> 编译器想，可能alist中元素是E，那么就存在这样的赋值: D d1 = new E(); 所以报错
         * 
         * ---分割线------------------------------
         * Object o1 = alist.get(0); ---> Object是所有类的父类，是最大的类。
         *                                alist中所有元素的类都不能大于Object，所以这个get是通过的。
         * 
         */
        Object o1 = alist.get(0);
//        E e1 = alist.get(0);      //编译错误
//        D d1 = alist.get(0);      //编译错误
//        C c1 = alist.get(0);      //编译错误
//        B b1 = alist.get(0);      //编译错误
//        A a1 = alist.get(0);      //编译错误
        
        
        /**
         * “?”代表未知类型
         * extends关键字声明了类型的上界，表示参数化的类型可能是所指定的类型，或者是此类型的子类
         * super关键字声明了类型的下界，表示参数化的类型可能是所指定的类型，或者是此类型的父类型，直至Object
         */
    }
}
