/**
 * Project: armory-core-1.1.2
 * 
 * File Created at 2011-7-9
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
package com.liubida.ThinkingInJAVA.jdiff;

/**
 * @author liubida
 */
public class ColorString {
    private String  str;

    private boolean isColor;

    public void setColor(boolean isColor) {
        this.isColor = isColor;
    }

    public ColorString(String content) {
        this.str = content;
        this.isColor = false;
    }

    public ColorString(String content, boolean isColor) {
        this.str = content;
        this.isColor = isColor;
    }

    public String toString() {
        if (isColor) {
            return "*" + str;
        } else {
            return str;
        }
    }

    /**
     * @return the str
     */
    public String getStr() {
        return str;
    }

    /**
     * @param str the str to set
     */
    public void setStr(String str) {
        this.str = str;
    }

    /**
     * @return the isColor
     */
    public boolean isColor() {
        return isColor;
    }

    /**
     * @param isColor the isColor to set
     */
}
