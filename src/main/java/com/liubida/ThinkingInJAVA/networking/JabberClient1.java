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
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author liubida
 */
public class JabberClient1 {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java JabberClient1 <client-port> <server-port");
            System.exit(1);
        }
        int cPort = Integer.valueOf(args[0]);
        int sPort = Integer.valueOf(args[1]);

        SocketChannel sChannel = SocketChannel.open();
        Selector sel = Selector.open();
        try {
            sChannel.configureBlocking(false);
            sChannel.socket().bind(new InetSocketAddress(cPort));
            sChannel.register(sel, SelectionKey.OP_CONNECT | SelectionKey.OP_READ
                    | SelectionKey.OP_WRITE);
            boolean written = false;
            boolean done = false;
            Charset charset = Charset.forName(System.getProperty("file.encoding"));
            ByteBuffer buf = ByteBuffer.allocate(16);
            int i = 0;
            while (!done) {
                //不调用remove是否表示多次select的key结果集会一直存在
                sel.select();
                Iterator it = sel.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    it.remove();
                    sChannel = (SocketChannel) key.channel();
                    if (key.isConnectable() && !sChannel.isConnected()) {
                        InetAddress addr = InetAddress.getByName(null);
                        boolean success = sChannel.connect(new InetSocketAddress(addr, sPort));
                        if (!success) {
                            sChannel.finishConnect();
                        }
                    }
                    if (key.isReadable() && written) {
                        if (sChannel.read((ByteBuffer) buf.clear()) > 0) {
                            written = false;
                            String response = charset.decode((ByteBuffer) buf.flip()).toString();
                            System.out.println(response);
                            if (response.indexOf("END") != -1)
                                done = true;
                        }
                    }
                    if (key.isWritable() && !written) {
                        if (i < 10) {
                            sChannel.write(ByteBuffer.wrap(new String("liubida " + i).getBytes()));
                        } else {
                            if (i == 10) {
                                sChannel.write(ByteBuffer.wrap(new String("END").getBytes()));
                            }
                        }
                        written = true;
                        i++;
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
