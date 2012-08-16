/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-16
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
package com.liubida.ThinkingInJAVA.enumerated;

/**
 * @author liubida
 */
public enum OzWitch {
    WEST("Miss Gulch, aka the Wicked Witch of the West", 10),
    NORTH("Glinda, the Good Witch of the North", 89),
    EAST("Wicked Witch of the East, wearer of the Ruby " + "Slippers, crushed by Dorothy’s house",
            67),
    SOUTH("Good by inference, but missing", 50);

    private String description;
    private int    proit;
    private OzWitch(String description, int proit) {
        this.description = description;
        this.setProit(proit);
    }

    public String getDescription() {
        return this.description;
    }

    public void setProit(int proit) {
        this.proit = proit;
    }

    public int getProit() {
        return proit;
    }
    public static void main(String[] args) {
        for (OzWitch o : OzWitch.values()) {
            System.out.println(o.getDescription());
        }
    }
}