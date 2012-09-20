/*
 * Copyright 2012 sohu.com All right reserved. This software is the
 * confidential and proprietary information of sohu.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with sohu.com.
 */
package com.liubida.sohu.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * 类Send.java的实现描述：TODO 类实现描述
 * 
 * @author liubida Sep 20, 2012 3:05:29 PM
 */
public class Send implements Runnable {

    public final static String QUEUE_NAME = "hello";
    public final static String domain = "localhost";

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Channel channel = null;
    private boolean bStop = true;

    @Override
    public void run() {
        bStop = false;
        factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            String exchangeName = "myExchange";
            String routingKey = "testRoute";
            // channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            int count = 0;
            while (!Thread.interrupted() && !bStop) {
                String message = "count:" + count++;
                // channel.basicPublish("", "hello", null, message.getBytes());
                channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,
                                     message.getBytes());
                System.out.println(" [>>] Send : " + message);
                // TimeUnit.SECONDS.sleep(random.nextInt(MAX));
                TimeUnit.SECONDS.sleep(2);
            }
            channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,
                                 "stop".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(" Send [*] ");
            if (null != channel) {
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean cancel() {
        return (bStop = true);
    }

    public boolean getStatus() {
        return !bStop;
    }

    public static void main(String[] argv) throws IOException {
        Send s = new Send();
        Recv r = new Recv();
        new Thread(s).start();
        new Thread(r).start();
        System.in.read();
        s.cancel();
        r.cancel();
    }
}
