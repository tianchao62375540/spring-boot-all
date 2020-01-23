package com.tc.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tc.config.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 13:57
 * @Description:
 */
public class TopicSend {
    public static final String queue_topic_one = "queue_topic_one";
    public static final String queue_topic_two = "queue_topic_two";
    public static final String queue_topic_three = "queue_topic_three";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        String message = "topic消息";

        channel.exchangeDeclare("exchange_topic", BuiltinExchangeType.TOPIC);
        channel.queueDeclare(queue_topic_one, true, false, false, null);
        channel.queueDeclare(queue_topic_two, true, false, false, null);
        channel.queueDeclare(queue_topic_three, true, false, false, null);
        channel.queueBind(queue_topic_one, "exchange_topic", "debug.*.B");//3
        channel.queueBind(queue_topic_two, "exchange_topic", "error.#");//9
        channel.queueBind(queue_topic_three, "exchange_topic", "*.email.*");//9
        String [] strings = new String[]{"error","debug","info"};
        String [] strings1 = new String[]{"user","order","email"};
        String [] strings2 = new String[]{"A","B","C"};
        for (String s : strings) {
            for (String s1 : strings1) {
                for (String s2 : strings2) {
                    String m = s+"."+s1+"."+s2;
                    channel.basicPublish("exchange_topic", m ,null, message.getBytes());
                    System.out.println(m);
                }
            }
        }
        channel.close();
        connection.close();
    }
}
