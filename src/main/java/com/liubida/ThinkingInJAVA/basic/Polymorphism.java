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
import static com.liubida.ThinkingInJAVA.util.Print.print;

class Shape {
    void draw() {
        print("Shape draw");
    }

    private void ok() {
        print("Shape ok");
    }
}

class Circle extends Shape {
    @Override
    void draw() {
        print("Circle draw");
    }
}

class Triangle extends Shape {
    void shout(){
        print("i'm a triangle!");
    }
}

class Square extends Shape {
    @Override
    void draw() {
        print("Square draw");
    }
}

class Child1Square extends Square {
    @Override
    void draw() {
        print("ChildSquare draw");
    }
}

class Child2Square extends Square {
}

public class Polymorphism {
    public static void main(String[] args) {
        Shape s = new Circle();
        Shape t = new Triangle();
        Shape q = new Square();
        Shape cq1 = new Child1Square();
        Shape cq2 = new Child2Square(); //upcasting to the nearest superClass‘s method
        s.draw();
        t.draw();
        ((Triangle)t).shout();
        q.draw();
        cq1.draw();
        cq2.draw();
    }
}
