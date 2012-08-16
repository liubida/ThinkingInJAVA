/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-30
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

import java.util.List;
import java.util.Map;

import com.liubida.ThinkingInJAVA.util.New;


/**
 * @author liubida
 */
public class ExplicitTypeSpecification {
    static void f(Map<String, List<Map<Integer, Integer>>> m) {
    }
    public static void main(String[] args) {
        f(New.<String, List<Map<Integer, Integer>>>map());
    }
}
