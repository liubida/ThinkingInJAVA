/*
 * Copyright 2012 sohu.com All right reserved. This software is the
 * confidential and proprietary information of sohu.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with sohu.com.
 */
package com.liubida.sohu.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

/**
 * 类Recv.java的实现描述：TODO 类实现描述
 * 
 * @author liubida Sep 20, 2012 4:33:21 PM
 */
public class Recv implements Runnable {

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private QueueingConsumer consumer = null;
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
            String queueName = "myQueue";
            String routingKey = "testRoute";
            boolean durable = true;
            channel.exchangeDeclare(exchangeName, "direct", durable);
            channel.queueDeclare(queueName, durable,false,false,null);
            channel.queueBind(queueName, exchangeName, routingKey);
            boolean noAck = false;

            consumer = new QueueingConsumer(channel);
            channel.queueDeclare(Send.QUEUE_NAME, false, false, false, null);
            channel.basicConsume(queueName, noAck, consumer);

            Delivery delivery = null;
            System.out.println(" [*] Waiting for messages. To exit press any key");
            while (!Thread.interrupted() && !bStop) {
                delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println(" [<<] Received : " + message + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(" Recv [*] ");
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
}
