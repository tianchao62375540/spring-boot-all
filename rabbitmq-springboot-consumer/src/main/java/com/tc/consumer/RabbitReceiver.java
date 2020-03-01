package com.tc.consumer;

import com.rabbitmq.client.Channel;
import com.tc.entity.Order;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Auther: tianchao
 * @Date: 2020/3/1 19:56
 * @Description:
 */
@Component
public class RabbitReceiver {

   /* @RabbitListener(
            bindings = {@QueueBinding(
                            value = @Queue(value = "queue-1",durable = "true"),
                            exchange = @Exchange(value = "exchange-1",durable = "true",
                                type = "topic",ignoreDeclarationExceptions = "true"),
                            key = {"springboot.#"}
                        )
            }

    )*/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1",
                    durable="true",arguments = {@Argument(name = "x-queue-type",value = "classic")}),
            exchange = @Exchange(value = "exchange-1",
                    durable="true",
                    type= "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
            )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws IOException {
        Object payload = message.getPayload();
        System.err.println("====================消费端消息体======================");
        System.err.println("消费端消息体:"+payload);
        Long tag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("amqp_deliveryTag:"+tag);
        channel.basicAck(tag,false);
    }

    /**
     * customer:
     *   rabbitmq:
     *     listener:
     *       order:
     *         queue:
     *           name: queue-order
     *           durable: true
     *         exchange:
     *           name: exchange-order
     *           durable: true
     *           type: topic
     *           ignoreDeclarationExceptions: true
     *         key: order.#
     * @param order
     * @param headers
     * @param channel
     * @throws IOException
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${customer.rabbitmq.listener.order.queue.name}",
                    durable="${customer.rabbitmq.listener.order.queue.durable}",arguments = {@Argument(name = "x-queue-type",value = "classic")}),
            exchange = @Exchange(value = "${customer.rabbitmq.listener.order.exchange.name}",
                    durable="${customer.rabbitmq.listener.order.exchange.durable}",
                    type= "${customer.rabbitmq.listener.order.exchange.type}",
                    ignoreDeclarationExceptions = "${customer.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
            key = "${customer.rabbitmq.listener.order.key}"
    )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        System.err.println("====================消费端消息体 对象方式======================");
        System.err.println("消费端order");
        Long tag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("amqp_deliveryTag:"+tag);
        channel.basicAck(tag,false);
    }
}
