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

import static com.liubida.ThinkingInJAVA.util.Sets.complement;
import static com.liubida.ThinkingInJAVA.util.Sets.difference;
import static com.liubida.ThinkingInJAVA.util.Sets.intersection;
import static com.liubida.ThinkingInJAVA.util.Sets.union;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liubida
 */

enum Watercolors {
    ZINC,
    LEMON_YELLOW,
    MEDIUM_YELLOW,
    DEEP_YELLOW,
    ORANGE,
    BRILLIANT_RED,
    CRIMSON,
    MAGENTA,
    ROSE_MADDER,
    VIOLET,
    CERULEAN_BLUE_HUE,
    PHTHALO_BLUE,
    ULTRAMARINE,
    COBALT_BLUE_HUE,
    PERMANENT_GREEN,
    VIRIDIAN_HUE,
    SAP_GREEN,
    YELLOW_OCHRE,
    BURNT_SIENNA,
    RAW_UMBER,
    BURNT_UMBER,
    PAYNES_GRAY,
    IVORY_BLACK
}

public class WatercolorSets {
    public static void main(String[] args) {
        Set<Watercolors> s1 = EnumSet.range(Watercolors.DEEP_YELLOW, Watercolors.YELLOW_OCHRE);
        Set<Watercolors> s2 = EnumSet.range(Watercolors.ZINC, Watercolors.PHTHALO_BLUE);
        System.out.println(union(s1, s2));
        System.out.println(intersection(s1, s2));
        System.out.println(difference(s1, s2));
        System.out.println(complement(s1, s2));
        Set<String> a = new HashSet<String>();
    }
}
