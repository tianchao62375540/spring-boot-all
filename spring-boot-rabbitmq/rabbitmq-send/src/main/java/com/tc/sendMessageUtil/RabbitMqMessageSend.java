package com.tc.sendMessageUtil;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 15:14
 * @Description:
 */
@Component
public class RabbitMqMessageSend {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMessage(String message,String routingKey,String exchange){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }


}
