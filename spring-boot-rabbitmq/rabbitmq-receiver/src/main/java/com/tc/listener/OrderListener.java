package com.tc.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 16:09
 * @Description:
 */
@Component
public class OrderListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final AtomicInteger num = new AtomicInteger(0);

    @RabbitListener(queues = "fanout_query_one")
    public void get(String message){
        System.out.println(message);
    }
    @RabbitListener(queues = "bootQueue",containerFactory = "simpleRabbitListenerContainerFactory")
    public void getbootQueue(String message){

        //int i = 1/0;
        System.out.println("getbootQueue...dddd........."+num.incrementAndGet());
        System.out.println(message);
    }
    @RabbitListener(queues = "bootQueue",containerFactory = "simpleRabbitListenerContainerFactory")
    public void getbootQueueObj(Message message, Channel channel) throws IOException {
        //int i = 1/0;
        System.out.println("getbootQueueObj............"+num.incrementAndGet());
        System.out.println(message);
        System.out.println(message.getBody());
        String correlationId = message.getMessageProperties().getCorrelationId();
        System.out.println(correlationId+"==============================");
        MessageProperties messageProperties = message.getMessageProperties();
        System.out.println(messageProperties.getReceivedRoutingKey());
        if (placeAnOrder()){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }else {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }

    }

    private boolean placeAnOrder() {
        return false;
    }

    //@RabbitListener(queues = "lifeQueue",containerFactory = "simpleRabbitListenerContainerFactory")
    public void processLife(Message message, Channel channel) throws IOException {
        System.out.println("processLife lifeQueue....");
        System.out.println(message);
        System.out.println(new String(message.getBody()));

        MessageProperties messageProperties = message.getMessageProperties();
        System.out.println(messageProperties.getReceivedRoutingKey());
        if (!placeAnOrder()){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }else {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @RabbitListener(queues = "deadQueue",containerFactory = "simpleRabbitListenerContainerFactory")
    public void processDead(Message message, Channel channel) throws IOException {
        System.out.println("processDead deadQueue....");
        System.out.println(message);
        System.out.println(new String(message.getBody()));

        MessageProperties messageProperties = message.getMessageProperties();
        System.out.println(messageProperties.getReceivedRoutingKey());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "cancelOrderDeadQueue",containerFactory = "simpleRabbitListenerContainerFactory")
    public void processCancelOrderDeadQueue(Message message, Channel channel) throws IOException {
        System.out.println("processCancelOrderDeadQueue cancelOrderDeadQueue....");
        System.out.println(message);
        System.out.println(new String(message.getBody()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
        //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        rabbitTemplate.convertAndSend("dingShiExchange", "order.two.insert", message.getBody());
    }


    @RabbitListener(queues = "dlxQueue",containerFactory = "simpleRabbitListenerContainerFactory")
    public void dlxQueue(Message message, Channel channel) throws IOException {
        System.out.println("dlxQueue...."+message);
        System.out.println(new String(message.getBody(),"UTF-8"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
        //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
