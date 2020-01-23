package com.tc.direct;

import com.rabbitmq.client.*;
import com.tc.config.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 12:16
 * @Description:
 */
public class DirectReceiverOne {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicConsume(DirectSend.queue_direct_one, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("routingKey:"+envelope.getRoutingKey());
                System.out.println(new String(body));
            }
        });

    }
}
