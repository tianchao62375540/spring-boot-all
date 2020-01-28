package com.tc.config;

import com.tc.config.properties.RabbitMqProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
        //connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        /*rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {

            }
        });*/
        return rabbitTemplate;
    }


    @Bean
    public DirectExchange directExchange(){
        Map<String,Object> map = new HashMap<>();
        //map.put("alternate-exchange", "exchangeTest");
        return new DirectExchange("boot_direct_exchange", true, false,map);
    }

    @Bean
    public Queue queue(){
        return new Queue("bootQueue", true);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct_routingKey");
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct_routingKey_two");
    }


    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        //手动确认
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    /*@Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

            }
        });
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return simpleMessageListenerContainer;
    }*/

    /**
     * ==============================关于私信交换机
     * @return
     */
    @Bean
    public Queue lifeQueue(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange","deadExchange");
        map.put("x-dead-letter-routing-key", "key.dead");
        Queue queue = new Queue("lifeQueue",true,false,false,map);
        return queue;
    }
    @Bean
    public Queue deadQueue(){
        Queue queue = new Queue("deadQueue",true,false,false,null);
        return queue;
    }
    @Bean
    public DirectExchange lifeExchange(){
        return new DirectExchange("lifeExchange");
    }

    @Bean
    public DirectExchange deadExchange(){
        DirectExchange directExchange = new DirectExchange("deadExchange");
        return directExchange;
    }

    @Bean
    public Binding bindLifeExchange(){
        return BindingBuilder.bind(lifeQueue()).to(lifeExchange()).with("key.life");
    }

    @Bean
    public Binding bindDeadExchange(){
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("key.dead");
    }


    @Bean
    public Queue orderQueue(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange","cancelOrderDeadExchange");
        map.put("x-dead-letter-routing-key", "order.cancel");
        map.put("x-message-ttl", 30000);
        Queue queue = new Queue("orderQueue",true,false,false,map);
        return queue;
    }

    @Bean
    public Queue cancelOrderDeadQueue(){
        Map<String,Object> map = new HashMap<>();
        Queue queue = new Queue("cancelOrderDeadQueue",true,false,false,map);
        return queue;
    }
    @Bean
    public DirectExchange cancelOrderDeadExchange(){
        return new DirectExchange("cancelOrderDeadExchange", true, false);
    }

    @Bean
    public DirectExchange orderQueueExchange(){
        return new DirectExchange("orderQueueExchange", true, false);
    }

    @Bean
    public Binding bindOrderQueue(){
        return BindingBuilder.bind(orderQueue()).to(orderQueueExchange()).with("order.insert");
    }
    @Bean
    public Binding bindCancelOrderDeadQueue(){
        return BindingBuilder.bind(cancelOrderDeadQueue()).to(cancelOrderDeadExchange()).with("order.cancel");
    }

}
