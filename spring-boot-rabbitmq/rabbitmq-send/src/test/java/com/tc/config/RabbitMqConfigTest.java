package com.tc.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 15:41
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMqConfigTest {

    @Autowired
    ConnectionFactory connectionFactory;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void connectionFactory() {
        System.out.println(connectionFactory);
    }

    @Test
    public void rabbitTemplate() {
        System.out.println(rabbitTemplate);
    }
}