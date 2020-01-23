package com.tc.topic;

import com.rabbitmq.client.*;
import com.tc.config.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 14:05
 * @Description:
 */
public class TopicReceiverTwo {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicConsume(TopicSend.queue_topic_three, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("routingKey:"+envelope.getRoutingKey());
                System.out.println(new String(body));
            }
        });

    }
}
