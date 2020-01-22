package com.tc.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: tianchao
 * @Date: 2020/1/22 17:33
 * @Description:
 */
public class ConnectionUtil {


    private static final ConnectionFactory FACTORY = new ConnectionFactory();

    static {
        FACTORY.setHost("127.0.0.1");
        FACTORY.setPort(5672);
        FACTORY.setVirtualHost("/myHost");
        FACTORY.setUsername("mq");
        FACTORY.setPassword("mq");
    }

    public static Connection getConnection() throws IOException, TimeoutException {
        return FACTORY.newConnection();
    }
}
