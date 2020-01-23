package com.tc.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 15:06
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMqConfigTest {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Test
    public void connectionFactory() {
        System.out.println(connectionFactory);
    }
}