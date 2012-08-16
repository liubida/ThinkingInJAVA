/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-21
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
package com.liubida.ThinkingInJAVA.networking;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Console {
    public static String title(Object o) {
        String t = o.getClass().toString();
        if (t.indexOf("class ") != -1) {
            t = t.substring(6);
        }
        return t;
    }

    public static void setupClosing(JFrame frame) {
        //设置关闭方式
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void run(JFrame frame, int width, int height) {
        setupClosing(frame);
        frame.setSize(width, height);
        frame.setVisible(true);
    }

    public static void run(JApplet applet, int width, int height) {
        JFrame frame = new JFrame(title(applet));
        setupClosing(frame);
        frame.getContentPane().add(applet);
        frame.setSize(width, height);
        applet.init();
        applet.start();
        frame.setVisible(true);
    }

    public static void run(JPanel panel, int width, int height) {
        JFrame frame = new JFrame(title(panel));
        setupClosing(frame);
        frame.getContentPane().add(panel);
        frame.setSize(width, height);
        frame.setVisible(true);
    }
} ///:~
