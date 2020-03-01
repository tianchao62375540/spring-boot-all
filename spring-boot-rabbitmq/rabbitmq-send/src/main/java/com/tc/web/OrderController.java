package com.tc.web;

import com.tc.sendMessageUtil.RabbitMqMessageSend;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 15:20
 * @Description:
 */
@RestController
public class OrderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitMqMessageSend rabbitMqMessageSend;

    @RequestMapping("/order")
    public ResponseEntity<String> order(String id,String routingKey,String exchange){
        //rabbitMqMessageSend.sendMessage1("订单号 "+id+" 下单了", routingKey, exchange);
        rabbitTemplate.convertAndSend("autoTTlQueue", (Object) id, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                UUID uuid = UUID.randomUUID();
                System.out.println("send :   "+uuid);
                CorrelationData correlationData = new CorrelationData(uuid.toString());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(simpleDateFormat.format(new Date()));
                Integer integer = Integer.valueOf(id)*1000;
                System.out.println(integer);
                message.getMessageProperties().setExpiration(integer+"");
                message.getMessageProperties().setCorrelationId(uuid.toString());
                return message;
            }
        });
        return ResponseEntity.ok("下单成功");
    }
}
