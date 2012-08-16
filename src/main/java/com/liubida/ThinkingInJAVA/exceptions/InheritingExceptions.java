/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-3
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
package com.liubida.ThinkingInJAVA.exceptions;

/**
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;

@SuppressWarnings("serial")
class simpleException extends Exception {
    public simpleException(){
        super();
    }
    public simpleException(String a){
        super(a);
    }
}

public class InheritingExceptions {
    public void f() throws simpleException {
        throw new simpleException("liubida");
    }

    public static void main(String[] args) {
        InheritingExceptions sed = new InheritingExceptions();
        try {
            sed.f();
        } catch (simpleException e) {
            e.printStackTrace(System.out);
            print(e.getMessage());
        }
    }
}
