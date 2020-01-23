package com.tc.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tc.config.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 12:13
 * @Description: direct类型交换机
 */
public class DirectSend {
    public static final String queue_direct_one = "query_user_insert";
    public static final String queue_direct_two = "query_user_update";
    public static final String queue_direct_three = "query_user_delete";

    public static final String exchange_direct = "exchange_direct";

    //public static final String routingKey_user_insert = "routingKey_user_insert";
    public static void main(String[] args) throws IOException, TimeoutException {
        String message = "direct消息";
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queue_direct_one, true, false, false, null);
        channel.queueDeclare(queue_direct_two, true, false, false, null);
        channel.queueDeclare(queue_direct_three, true, false, false, null);
        channel.exchangeDeclare(exchange_direct, BuiltinExchangeType.DIRECT);
        channel.queueBind(queue_direct_one, exchange_direct, "user.insert");
        channel.queueBind(queue_direct_one, exchange_direct, "user.update");
        channel.queueBind(queue_direct_two, exchange_direct, "user.update");
        channel.queueBind(queue_direct_three, exchange_direct, "user.delete");
        channel.basicPublish(exchange_direct, "user.delete" ,null, message.getBytes());
        channel.close();
        connection.close();
    }
}
