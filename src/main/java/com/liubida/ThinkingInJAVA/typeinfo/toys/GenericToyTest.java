/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-18
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
package com.liubida.ThinkingInJAVA.typeinfo.toys;

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;
public class GenericToyTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Class<FancyToy> ft = FancyToy.class;
        FancyToy fancyToy = ft.newInstance();
        
        Class<? super FancyToy> t = ft.getSuperclass();
        Toy toy1 = (Toy) t.newInstance();
        
        Class<Toy> tt = (Class<Toy>)ft.getSuperclass();
        Toy toy2 = tt.newInstance();
        
        print(fancyToy);
        print(toy1);
        print(toy2);
    }
}
