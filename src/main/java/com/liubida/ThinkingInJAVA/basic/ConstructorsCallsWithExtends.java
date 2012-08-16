package com.liubida.ThinkingInJAVA.basic;
/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-26
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
 * @author liubida
 *
 */
import static com.liubida.ThinkingInJAVA.util.Print.*;

class Meal {
    Meal() {
        print("Meal()");
    }
}

class Bread {
    Bread() {
        print("Bread()");
    }
}

class Cheese {
    Cheese() {
        print("Cheese()");
    }
}

class Lettuce {
    Lettuce() {
        print("Lettuce()");
    }
}

class Lunch extends Meal {
    Lunch() {
        print("Lunch()");
    }
}

class PortableLunch extends Lunch {
    PortableLunch() {
        print("PortableLunch()");
    }
}

@SuppressWarnings("unused")
class Sandwich extends PortableLunch {
    private Bread   b = new Bread();
    private Cheese  c = new Cheese();
    private Lettuce l = new Lettuce();

    public Sandwich() {
        print("Sandwich()");
    }

}

public class ConstructorsCallsWithExtends {
    public static void main(String[] args) {
        /**
         * 通过创建Sandwich来查看构造函数的调用关系
         */
        new Sandwich();
        
        new Glyph();
        /** 
         * 这里创建了一个RoundGlyph对象，但是由于它是一个derived-class，
         * 所以需要首先调用其父类的构造函数。
         * 在其父类构造函数中，又调用了一个被重载的draw函数，
         * 所以父类(Glyph)就在子类(RoundGlyph)创建之前，调用了其子类的draw函数。
         * 注意，在draw函数中涉及到子类的radius变量，由于此时子类还没有开始创建，所以radius为 0
         */
        new RoundGlyph(5);
    }
}

class Glyph {
    Glyph() {
        print("Glyph() before draw()");
        draw();
        print("Glyph() after draw()");
    }

    void draw() {
        print("Glyph draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;

    RoundGlyph(int r) {
        this.radius = r;
        print("RoundGlyph: radius=" + radius);
    }

    void draw() {
        print("RoundGlyph draw(): radius=" + radius);
    }
}
