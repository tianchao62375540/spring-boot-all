package com.tc.config;

import com.alibaba.fastjson.JSON;
import com.tc.config.properties.RabbitMqProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.MessagingMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 14:31
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(value={RabbitMqProperties.class})
public class RabbitMqConfig {
    @Autowired
    private RabbitMqProperties rabbitMqProperties;

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMqProperties.getHostname(),rabbitMqProperties.getPort());
        connectionFactory.setUsername(rabbitMqProperties.getUsername());
        connectionFactory.setPassword(rabbitMqProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitMqProperties.getVirtualHost());
        //设置开启发送方确认模式
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                //确认的订单
                //**System.out.println(correlationData.getId());
                System.out.println("ack:"+ack);
                System.out.println("原因:"+ cause);
                System.out.println("confirm:"+Thread.currentThread().hashCode());
            }
        });
        //开启失败回调
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             *
             * @param message 发送的消息内容以及消息的配置信息
             * @param replyCode 状态码
             * @param replyText 状态码文字描述
             * @param exchange
             * @param routingKey
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("消息:"+message);
                System.out.println("状态码:"+replyCode);
                System.out.println("状态码文字描述:"+replyText);
                System.out.println("交换机:"+exchange);
                System.out.println("交换机:"+exchange);
                System.out.println("RoutingKey:"+routingKey);
                System.out.println("returnedMessage:"+Thread.currentThread().hashCode());
            }
        });

        rabbitTemplate.setMessageConverter(new MessageConverter() {
            @Override
            public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
                messageProperties.setContentType("text/xml");
                messageProperties.setContentEncoding("utf-8");
                Message message = new Message(JSON.toJSONString(object).getBytes(),messageProperties );
                return message;
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return message;
            }
        });
        return rabbitTemplate;
    }



}
