/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-17
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
package com.liubida.ThinkingInJAVA.strings;

/**
 * @author liubida
 *
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.liubida.ThinkingInJAVA.util.Print.*;

public class Groups {
    static final String POEM = "Twas brillig, and the slithy toves\n"
                                     + "Did gyre and gimble in the wabe.\n"
                                     + "All mimsy were the borogoves,\n"
                                     + "And the mome raths outgrabe.\n\n"
                                     + "Beware the Jabberwock, my son,\n"
                                     + "The jaws that bite, the claws that catch.\n"
                                     + "Beware the Jubjub bird, and shun\n"
                                     + "The frumious Bandersnatch.";

    public static void main(String[] args) {
        Matcher m = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$").matcher(POEM);
        print(m.groupCount());
    }
}
