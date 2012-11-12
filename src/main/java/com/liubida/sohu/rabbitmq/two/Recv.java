package com.liubida.sohu.rabbitmq.two;

import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Recv implements Runnable {

    private final static boolean AUTO_ACK = false;

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private QueueingConsumer consumer = null;
    private Channel channel = null;
    private boolean bStop = true;

    public Recv(){
        factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost(Send.DOMAIN);
        factory.setPort(Send.PORT);
    }

    @Override
    public void run() {
        bStop = false;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.basicQos(5);
            consumer = new QueueingConsumer(channel);
            
            channel.basicConsume(Send.QUEUE_HELLO_DURABLE, AUTO_ACK, consumer);
            Delivery delivery = null;
            System.out.println(" [*] Waiting for messages. To exit press any key");
            while (!Thread.interrupted() && !bStop) {
                delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println(" [<<] Received : " + message);
                TimeUnit.MILLISECONDS.sleep(400);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
            bStop = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(" Recv [end] ");
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
