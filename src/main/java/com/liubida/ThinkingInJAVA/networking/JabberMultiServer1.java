/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-22
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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author liubida
 */
public class JabberMultiServer1 {
    public static final int PORT = 61062;

    public static void main(String[] args) throws IOException {
        Charset charset = Charset.forName(System.getProperty("file.encoding"));
        ByteBuffer buf = ByteBuffer.allocate(16);
        SocketChannel sChannel = null;
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        Selector sel = Selector.open();
        try {
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(PORT));
            serverChannel.register(sel, SelectionKey.OP_ACCEPT);

            System.out.println("Server on port: " + PORT);
            while (true) {
                sel.select();
                Iterator it = sel.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        sChannel = serverChannel.accept();
                        System.out.println("Accepted connection from:" + sChannel.socket());
                        sChannel.configureBlocking(false);
                        sChannel.register(sel, SelectionKey.OP_READ);
                    } else {
                        sChannel = (SocketChannel) key.channel();
                        sChannel.read(buf);
                        String response = charset.decode((ByteBuffer) buf.flip()).toString();
                        System.out.println("Echo: " + response);
                        sChannel.write((ByteBuffer) buf.rewind());
                        if (response.indexOf("END") != -1) {
                            sChannel.close();
                        }
                        buf.clear();
                    }
                }
            }

        } catch (Exception e) {

        } finally {
            if (sChannel != null)
                sChannel.close();
            serverChannel.close();
            sel.close();
        }
    }
}
