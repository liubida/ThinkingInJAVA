/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-19
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
package com.liubida.ThinkingInJAVA.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liubida
 */

public class TypeCounter extends HashMap<Class<?>, Integer> {
    private static final long serialVersionUID = 1L;
    
    private Class<?>          baseType;

    public TypeCounter(Class<?> baseType) {
        this.baseType = baseType;
    }

    private void countClass(Class<?> type) {
        Integer quantity = get(type);
        put(type, null == quantity ? 1 : quantity + 1);
        Class<?> superClass = type.getSuperclass();
        if (null != superClass && baseType.isAssignableFrom(superClass)) {
            countClass(superClass);
        }
    }
    public void count(Object o){
        Class<?> type = o.getClass();
        if(!baseType.isAssignableFrom(type)){
            throw new RuntimeException(o + " incorrect type: "
                    + type + ", should be type or subtype of "
                    + baseType);
        }
        countClass(type);
    }
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (Map.Entry<Class<?>, Integer> pair : entrySet()) {
            result.append(pair.getKey().getSimpleName());
            result.append("=");
            result.append(pair.getValue());
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }
}
