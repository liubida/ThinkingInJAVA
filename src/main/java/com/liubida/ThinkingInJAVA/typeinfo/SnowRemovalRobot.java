/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-24
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
package com.liubida.ThinkingInJAVA.typeinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liubida
 */
interface Operation {
    String description();

    void command();
}

interface Robot {
    String name();

    String model();

    List<Operation> operations();

}

public class SnowRemovalRobot implements Robot {
    private String name;

    public SnowRemovalRobot(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String model() {
        return "X11";
    }

    @Override
    public List<Operation> operations() {
        List<Operation> retList = new ArrayList<Operation>();
        retList.add(new Operation() {
            public String description() {
                return name + "can shovel snow";
            }
            public void command(){
                System.out.println(name+"is shoveling snow...");
            }
        });
        return retList;
    }

}
