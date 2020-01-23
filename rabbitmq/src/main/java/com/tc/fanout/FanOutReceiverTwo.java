package com.tc.fanout;

import com.rabbitmq.client.*;
import com.tc.config.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 11:41
 * @Description:
 */
public class FanOutReceiverTwo {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("fanout_query_two", true, false, false, null);
        channel.exchangeDeclare(FanOutSend.exchange, BuiltinExchangeType.FANOUT,true);
        channel.queueBind("fanout_query_two", FanOutSend.exchange, "");
        channel.basicConsume("fanout_query_two", true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("routingkey:"+envelope.getRoutingKey());
                System.out.println(new String(body)+"队列fanout_query_two");
            }
        });
    }
}
