package com.tc.web;

import com.tc.sendMessageUtil.RabbitMqMessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 15:20
 * @Description:
 */
@RestController
public class OrderController {

    @Autowired
    RabbitMqMessageSend rabbitMqMessageSend;

    @RequestMapping("/order")
    public ResponseEntity<String> order(String id,String routingKey,String exchange){
        rabbitMqMessageSend.sendMessage("订单号 "+id+" 下单了", routingKey, exchange);
        return ResponseEntity.ok("下单成功");
    }
}
