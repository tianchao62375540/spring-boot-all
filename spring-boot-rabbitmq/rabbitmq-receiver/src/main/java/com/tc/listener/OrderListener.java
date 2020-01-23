package com.tc.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 16:09
 * @Description:
 */
@Component
public class OrderListener {

    @RabbitListener(queues = "fanout_query_one")
    public void get(String message){
        System.out.println(message);
    }
}
