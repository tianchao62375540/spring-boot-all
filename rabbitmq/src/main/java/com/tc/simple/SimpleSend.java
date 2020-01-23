package com.tc.simple;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tc.config.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: tianchao
 * @Date: 2020/1/22 17:36
 * @Description:
 */
public class SimpleSend {

    private final static String QUEUE_NAME = "mq_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        //连接中创建通道
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //channel.exchangeDeclare("simple",BuiltinExchangeType.TOPIC)
        // 消息内容
        String message = "简单的消息";
        // 向指定的队列中发送消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        for (int i = 0; i < 50; i++) {
            // 消息内容
            message = "  task .. " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            Thread.sleep(5000);
            System.out.println(" [x] Sent '" + message + "'");
        }


        //关闭通道和连接
        channel.close();
        connection.close();


    }
}
