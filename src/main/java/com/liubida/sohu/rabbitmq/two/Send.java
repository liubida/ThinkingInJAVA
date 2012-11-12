package com.liubida.sohu.rabbitmq.two;

import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Send implements Runnable {

    // public final static String QUEUE_HELLO = "hello";
    public final static String QUEUE_HELLO_DURABLE = "hello_durable";
    public final static String DOMAIN = "localhost";
    public final static int PORT = 5672;

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Channel channel = null;
    private boolean bStop = true;

    public Send(){
        factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost(DOMAIN);
        factory.setPort(PORT);
    }

    @Override
    public void run() {
        bStop = false;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_HELLO_DURABLE, true, false, false, null);
            int count = 0;
            String message = null;
            while (!Thread.interrupted() && !bStop) {
                message = "count:" + count++;
                channel.basicPublish("", QUEUE_HELLO_DURABLE, MessageProperties.PERSISTENT_TEXT_PLAIN,
                                     message.getBytes());
                System.out.println(" [>>] Send : " + message);
                // TimeUnit.SECONDS.sleep(0.1);
                TimeUnit.MILLISECONDS.sleep(100);
            }
            channel.basicPublish("", QUEUE_HELLO_DURABLE, null, "stop".getBytes());
            bStop = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(" Send [end] ");
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

    public static void main(String[] argv) throws Exception {
//        Send s = new Send();
        Recv r1 = new Recv();
        Recv r2 = new Recv();
//        new Thread(s).start();
        TimeUnit.SECONDS.sleep(5);
        new Thread(r1).start();
        new Thread(r2).start();
        System.in.read();
//        s.cancel();
        r1.cancel();
        r2.cancel();
    }
}
