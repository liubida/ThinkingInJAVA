package com.liubida.ThinkingInJAVA.basic;
/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-25
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

/**
 * 
 * @author liubida
 */
@SuppressWarnings("unused")
class WithFinal {
    final void g() {
    }

    private void f() {
    }
}

public class FinalMethod extends WithFinal {

    /**
     * method->g() 不能被重载 
     * public void g() { }
     */
    /**
     * method->f() 不能被重载，你可以加上@Override试试
     * 但是可以被重写，下面的f是一个新的method
     */
    public void f() {
    }
}
