package com.tc.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tc.config.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 10:02
 * @Description:
 */
public class FanOutSend {
    public static final String exchange = "fanout_exchange";
    public static final String routingKey = "fanout_exchange_key..";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //fanout交换机   发送者根本不知道消费者是谁  只绑定交换机
        channel.exchangeDeclare("fanout_exchange", BuiltinExchangeType.FANOUT);
        for (int i = 0; i < 50; i++) {
            String message = "fanout的消息 "+i;
            channel.basicPublish("fanout_exchange", "",null , message.getBytes());
            System.out.println("发送"+message+" 成功");
            Thread.sleep(5000);
        }
        channel.close();
        connection.close();
    }
}
