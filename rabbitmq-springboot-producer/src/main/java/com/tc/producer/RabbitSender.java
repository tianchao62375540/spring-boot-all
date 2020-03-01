package com.tc.producer;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: tianchao
 * @Date: 2020/3/1 19:06
 * @Description:
 */
@Component
public class RabbitSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("==========发送端confirm=============" + Thread.currentThread().getName());
            System.err.println("correlationData: "+correlationData);
            System.err.println("ack: " + ack);
            System.err.println("cause:" + cause);
            if (!ack){
                System.err.println("异常处理");
            }
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.err.println("==========发送端return============="+Thread.currentThread().getName());
            String correlationId = message.getMessageProperties().getCorrelationId();
            System.out.println("heads:"+message.getMessageProperties().getHeaders());
            System.err.println("correlationId:"+correlationId);
            System.err.println("return exchange: "+ exchange + ",routingKey:"+routingKey +",replyCode: "+replyText +",replyText: "+replyText);
        }
    };

    public void send(String exchange,String routingKey,Object message, Map<String,Object> properties){
        MessageHeaders mhs = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, mhs);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData("0987654321");
        rabbitTemplate.convertAndSend("exchange-1", "springboot.hello", message,correlationData);
    }
}
