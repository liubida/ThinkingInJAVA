/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-3
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
package com.liubida.ThinkingInJAVA.io;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.StringReader;

/**
 * @author liubida
 */
public class Try {
    InputStream a = new StringBufferInputStream("liubida");
    Reader      b = new StringReader("liubida");
    
}
