package com.tc.sendMessageUtil;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message,correlationData);
    }
    public void sendMessage1(String message,String routingKey,String exchange){
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(message);
        Map<String,Object> map = new HashMap<>();
        map.put("data", message);
        map.put("date", new Date());
        map.put("name", "田超");
        rabbitTemplate.convertAndSend(exchange, routingKey, map,correlationData);
    }


}
