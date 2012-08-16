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

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * @author liubida
 */
public class ShowHTML extends JApplet {
    JButton send = new JButton("Go");

    JLabel  l    = new JLabel();

    public void init() {
        send.addActionListener(new Al());

        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(send);
        cp.add(l);
    }

    class Al implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                URL u = new URL(getDocumentBase(), "http://www.baidu.com");
                getAppletContext().showDocument(u);
            } catch (Exception e1) {
                l.setText(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        Console.run(new ShowHTML(), 200, 180);
    }
}
